package com.engineer.gateway.service;

import com.engineer.common.utils.CookieUtil;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Lemon
 * @date 2020/3/7 19:36
 */
@Service
public class AuthService {

    private static final String KEY_PREFIX = "user_token:";
    private static final long TOKEN_VALIDITY_SECONDS = 3600;

    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 查询身份令牌
     * @param request
     * @return
     */
    public String getTokenFromCookie(HttpServletRequest request) {
        Map<String, String> cookieMap = CookieUtil.readCookie(request, "uid");
        String token = cookieMap.get("uid");
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        return token;
    }

    /**
     * 从header中查询Jwt令牌
     * @param request
     * @return
     */
    public String getJwtFromHeader(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isEmpty(authorization)) {
            return null;
        }
        if (!authorization.startsWith("Bearer ")) {
            return null;
        }
        String jwt = authorization.substring(7);
        return jwt;
    }

    /**
     * 查询令牌的有效期
     * @param accessToken
     * @return
     */
    public long getExpire(String accessToken) {
        String key = KEY_PREFIX + accessToken;
        long expire = redisTemplate.getExpire(key);
        return expire;
    }

    public void extendExpire(String accessToken) {
        String key = KEY_PREFIX + accessToken;
        redisTemplate.expire(key, TOKEN_VALIDITY_SECONDS, TimeUnit.SECONDS);
    }
}
