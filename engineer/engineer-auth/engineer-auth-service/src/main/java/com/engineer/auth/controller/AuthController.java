package com.engineer.auth.controller;

import com.engineer.auth.config.AuthProperties;
import com.engineer.auth.service.AuthService;
import com.engineer.auth.vo.AuthToken;
import com.engineer.auth.vo.LoginDetail;
import com.engineer.common.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Lemon
 * @date 2019/9/9 15:19
 */
@EnableConfigurationProperties(AuthProperties.class)
@RestController
public class AuthController {

    @Autowired
    private AuthProperties authProperties;

    @Autowired
    private AuthService authService;

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginDetail loginDetail) {
        // 申请用户身份令牌
        String jti = authService.login(loginDetail.getUserAccount(), loginDetail.getPassword());
        // 将令牌存储到cookie
        this.saveCookie(jti);
        return ResponseEntity.status(HttpStatus.CREATED).body(jti);
    }

    @GetMapping("userInfo")
    public ResponseEntity<String> userInfo(@RequestParam("token") String token) {
        AuthToken userInfo = authService.getUserInfo(token);
        return ResponseEntity.ok(userInfo.getAccessToken());
    }

    @PostMapping("logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        String token = CookieUtil.readCookie(request, "uid").get("uid");
        authService.delToken(token);
        CookieUtil.addCookie(response, authProperties.getCookieDomain(), "/", "uid", token, 0, false);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 将用户身份令牌存储到cookie
     * @param token
     */
    private void saveCookie(String token) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //添加cookie 认证令牌，最后一个参数设置为false，表示允许浏览器获取
        CookieUtil.addCookie(response, authProperties.getCookieDomain(), "/", "uid", token, authProperties.getCookieMaxAge(), false);
    }

    private void clearCookie(String token) {

    }
}
