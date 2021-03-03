package com.engineer.auth.service.impl;

import com.engineer.auth.config.AuthProperties;
import com.engineer.auth.service.AuthService;
import com.engineer.auth.vo.AuthToken;
import com.engineer.cilent.EngineerServiceList;
import com.engineer.common.enums.impl.AuthExceptionEnum;
import com.engineer.common.exceptions.ServiceException;
import com.engineer.common.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Lemon
 * @date 2019/9/9 15:30
 */
@Slf4j
@EnableConfigurationProperties(AuthProperties.class)
@Service
public class AuthServiceImpl implements AuthService {

    private static final String KEY_PREFIX = "user_token:";
    private static final String ERROR_DESCRIPTION = "error_description";
    private static final String BAD_EVIDENCE = "坏的凭证";
    private static final String USER_RETURN_NULL = "UserDetailsService returned null";

    @Autowired
    private AuthProperties authProperties;

    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String login(String userAccount, String password) {
        if (StringUtils.isEmpty(userAccount) || StringUtils.isEmpty(password)) {
            throw new ServiceException(AuthExceptionEnum.AUTH_LOGIN_PARAM_WRONG);
        }
        // 向Spring Security申请令牌
        AuthToken authToken = this.applyToken(userAccount, password, authProperties.getClientId(), authProperties.getClientSecret());
        if (authToken == null){
            throw new ServiceException(AuthExceptionEnum.AUTH_LOGIN_APPLYTOKEN_FAIL);
        }
        //
        String jti = authToken.getJti();
        String jsonString = JsonUtils.objectToJson(authToken);
        boolean result = this.saveToken(jti, jsonString, authProperties.getTokenValiditySeconds());
        if (!result) {
             log.error("保存token失败");
            throw new ServiceException(AuthExceptionEnum.AUTH_LOGIN_TOKEN_SAVEFAIL);
        }
        return jti;
    }

    @Override
    public AuthToken getUserInfo(String token) {
        String userToken = KEY_PREFIX + token;
        String userTokenString = redisTemplate.opsForValue().get(userToken);
        if (userTokenString == null) {
            throw new ServiceException(AuthExceptionEnum.AUTH_GET_USER_INFO_ERROR);
        }
        AuthToken authToken = JsonUtils.jsonToPojo(userTokenString, AuthToken.class);
        if (authToken == null) {
            throw new ServiceException(AuthExceptionEnum.AUTH_GET_USER_INFO_ERROR);
        }
        return authToken;
    }

    @Override
    public void delToken(String token) {
        String userToken = KEY_PREFIX + token;
        redisTemplate.delete(userToken);
    }

    /**
     * 存储令牌到Redis
     * @param accessToken 用户身份令牌
     * @param content AuthToken对象的内容
     * @param tokenValiditySeconds 过期时间
     * @return
     */
    private boolean saveToken(String accessToken, String content, long tokenValiditySeconds) {
        String key = KEY_PREFIX + accessToken;
        redisTemplate.boundValueOps(key).set(content, tokenValiditySeconds, TimeUnit.SECONDS);
        Long expire = redisTemplate.getExpire(key, TimeUnit.SECONDS);
        return expire > 0;
    }

    /**
     * 申请令牌
     * @param userAccount
     * @param password
     * @param clientId
     * @param clientSecret
     * @return
     */
    private AuthToken applyToken(String userAccount, String password, String clientId, String clientSecret) {
        // 从Eureka中获取认证服务的地址（因为Spring Security在认证服务中）
        // 从Eureka中获取认证服务的一个实例的地址
        ServiceInstance serviceInstance = loadBalancerClient.choose(EngineerServiceList.ENGINEER_SERVICE_AUTH);
        if (serviceInstance == null) {
            log.error("获取认证服务实例失败");
            throw new ServiceException(AuthExceptionEnum.AUTH_LOGIN_AUTH_SERVER_NOTFOUND);
        }
        URI uri = serviceInstance.getUri();
        // 令牌申请的地址
        String authUrl = uri.toString() + "/oauth/token";
//        System.out.println("authUrl = " + authUrl);
        // 定义header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(clientId, clientSecret);
        // 定义body
        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add(OAuth2Utils.GRANT_TYPE, "password");
        body.add("username", userAccount);
        body.add("password", password);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, headers);
        System.out.println(httpEntity.toString());
        Map bodyMap = null;
        try {
            // 设置restTemplate远程调用的时候，对400和401不让报错，正确返回数据
            restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
                @Override
                public void handleError(ClientHttpResponse response) throws IOException {
                    if (response.getRawStatusCode() != HttpStatus.BAD_REQUEST.value()
                            && response.getRawStatusCode() != HttpStatus.UNAUTHORIZED.value()) {
                        super.handleError(response);
                    }
                }
            });
            // http请求Spring Security的申请令牌接口
            ResponseEntity<Map> exchange = restTemplate.exchange(authUrl, HttpMethod.POST, httpEntity, Map.class);
            bodyMap = exchange.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("申请令牌失败: {}", e.getMessage());
            throw new ServiceException(AuthExceptionEnum.AUTH_LOGIN_APPLYTOKEN_FAIL);

        }

        if (bodyMap == null
                || bodyMap.get(OAuth2AccessToken.ACCESS_TOKEN) == null
                || bodyMap.get(OAuth2AccessToken.REFRESH_TOKEN) == null
                || bodyMap.get("jti") == null){
            // 获取Spring Security返回的错误信息
            String errMsg = (String) bodyMap.get(ERROR_DESCRIPTION);
            if (StringUtils.isNotEmpty(errMsg)){
                throw new ServiceException(AuthExceptionEnum.AUTH_CREDENTIAL_ERROR);
            }
        }
        AuthToken authToken = new AuthToken();
        // 用户身份令牌
        authToken.setAccessToken((String) bodyMap.get(OAuth2AccessToken.ACCESS_TOKEN));
        // 刷新令牌
        authToken.setRefreshToken((String) bodyMap.get(OAuth2AccessToken.REFRESH_TOKEN));
        // jwt令牌的唯一标识作为用户身份令牌
        authToken.setJti((String) bodyMap.get("jti"));
        return authToken;
    }

    /**
     * 获取httpBasic的串
     * @param clientId
     * @param clientSecret
     * @return
     */
    private String getHttpBasic(String clientId, String clientSecret) {
        String string = clientId + ":" +clientSecret;
        // 将串进行base64编码
        byte[] encode = Base64Utils.encode(string.getBytes());
        return "Basic " + new String(encode);
    }
}
