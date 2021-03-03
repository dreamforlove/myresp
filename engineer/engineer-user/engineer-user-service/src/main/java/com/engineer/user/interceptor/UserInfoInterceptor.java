package com.engineer.user.interceptor;

import com.engineer.common.vo.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lemon
 * @date 2020/4/13 15:23
 */
public class UserInfoInterceptor implements HandlerInterceptor {

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String PUBLIC_KEY = "publickey.txt";

    private static final ThreadLocal<UserInfo> USER_INFO_THREAD_LOCAL = new ThreadLocal<>();

    private static final String AUTH_PREFIX = "Basic ";
    private static final String JWT_PREFIX = "Bearer ";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // return true;
        String authorization = request.getHeader("Authorization");
        System.out.println(authorization);
        if (StringUtils.isNotBlank(authorization) && authorization.startsWith(AUTH_PREFIX)) {
            return true;
        }
        authorization = StringUtils.removeStart(authorization, JWT_PREFIX);
        if (StringUtils.isNotBlank(authorization)) {
            Jwt decode = JwtHelper.decodeAndVerify(authorization, new RsaVerifier(getPubKey()));
            String claims = decode.getClaims();
            HashMap<String, Object> hashMap = objectMapper.readValue(claims, HashMap.class);
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(Long.parseLong((String) hashMap.get("userId")));
            userInfo.setUserAccount((String) hashMap.get("userAccount"));
            userInfo.setUserName((String) hashMap.get("userName"));
            userInfo.setMenu((List<String>) hashMap.get("menu"));
            userInfo.setAuthorities((List<String>) hashMap.get("authorities"));
            USER_INFO_THREAD_LOCAL.set(userInfo);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        USER_INFO_THREAD_LOCAL.remove();
    }

    public static UserInfo getUserInfo(){
        return USER_INFO_THREAD_LOCAL.get();
    }

    /**
     * 获取非对称加密公钥 Key
     * @return 公钥 Key
     */
    private String getPubKey() {
        Resource resource = new ClassPathResource(PUBLIC_KEY);
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream());
            BufferedReader br = new BufferedReader(inputStreamReader);
            return br.lines().collect(Collectors.joining("\n"));
        } catch (IOException ioe) {
            return null;
        }
    }
}
