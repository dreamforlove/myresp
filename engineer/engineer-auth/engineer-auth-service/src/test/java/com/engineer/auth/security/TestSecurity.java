package com.engineer.auth.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Lemon
 * @date 2019/10/20 17:49
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestSecurity {
    @Test
    public void testPasswordEncoder(){
        String password = "123456";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        for(int i = 0; i<10; i++) {
            //每个计算出的Hash值都不一样
            String hashPass = passwordEncoder.encode(password);
            System.out.println(hashPass);
            //虽然每次计算的密码Hash值不一样但是校验是通过的
            boolean f = passwordEncoder.matches(password, hashPass);
            System.out.println(f);
        }
    }

    //校验jwt令牌
    @Test
    public void testVerify(){
        //公钥
        String publickey = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgfxJQI6iU4pPNiWJEANvfmFcspa5JNSeln5x/+MumwpWFB/kwU+vL/eXMxFmzUkBiR9zbdOYCHmm7AC00CTh/XRnFPhNSE9San5TG6hcocnjvtCauljFGZM/VbSlWr9fAXOaInJpaP67MPXFuX69XdFkj8BMcEhWxCM5eCEao4HwfUo4YHWCQkXGWiBcpt7nkaainwWYdVrdZCnWQvSZBD3acFkxts/Ka+YJRCmifXrBBVY3c/4ufjlB+Q84Omqa0eaGZTWqB2AG4rdNjWp87ygLHKGHDN08qkFdd36rXHh9mPEQKGejDUhbTOLusVpO2LYAN4V1y8pfFzoSoyqvUwIDAQAB-----END PUBLIC KEY-----";
        //jwt令牌
        String jwtString = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyQWNjb3VudCI6ImFkbWluIiwic2NvcGUiOlsicmVhZCJdLCJhdmF0YXJQYXRoIjoiaHR0cHM6Ly9maWxlLml2aWV3dWkuY29tL2Rpc3QvYTBlODhlODM4MDBmMTM4Yjk0ZDI0MTQ2MjFiZDk3MDQucG5nIiwidXNlck5hbWUiOiLnrqHnkIblkZgiLCJtZW51IjpbIi9sb2NhdGlvbi9tYW5hZ2VtZW50IiwiL3VzZXIvbWFuYWdlbWVudCIsIi9hdXRoL21hbmFnZW1lbnQiLCIvYXV0aC91c2VyZ3JwIiwiL2F1dGgvcGVybWlzc2lvbiIsIi9hdXRoL3JvbGUiLCIvb3JnYS9tYW5hZ2VtZW50Il0sImV4cCI6MTU4Njc0OTQ1MSwidXNlcklkIjoiMTA0ODEwODMxMjg5NTI5NTQ4OCIsImF1dGhvcml0aWVzIjpbIi91c2VyL2xpc3QiLCIvdXNlcmdycC92ZXJpZnkiLCIvb3JnYS92ZXJpZnkiLCIvdXNlcmdycC9kaXNhYmxlZCIsIi9vcmdhL2Rpc2FibGVkIiwiL3VzZXJncnAvZW5hYmxlZCIsIi9vcmdhL2VuYWJsZWQiLCIvdXNlcmdycC91cGRhdGUiLCIvcGVybWlzc2lvbi9kZWxldGUiLCIvcGVybWlzc2lvbi91cGRhdGUiLCIvbG9jYXRpb24vZGVsZXRlIiwiL3JvbGUvZW5hYmxlZCIsIi9yb2xlL3VzZXJncnAvYXV0aG9yaXphdGlvbiIsIi9sb2NhdGlvbi91cGRhdGUiLCIvcGVybWlzc2lvbi9lbmFibGVkIiwiL3JvbGUvZGlzYWJsZWQiLCIvdXNlcmdycC9kZWxldGUiLCIvcm9sZS9hZGQiLCIvcGVybWlzc2lvbi9hZGQiLCIvdXNlcmdycC9hZGQiLCIvcm9sZS91cGRhdGUiLCIvbG9jYXRpb24vYWRkIiwiL3Blcm1pc3Npb24vcm9sZS9hdXRob3JpemF0aW9uIiwiL3JvbGUvZGVsZXRlIiwiL3VzZXIvZW5hYmxlZCIsIi9wZXJtaXNzaW9uL2Rpc2FibGVkIiwiL3VzZXIvZGlzYWJsZWQiXSwianRpIjoiNmVlNGJkYjktYjQyNC00YzNhLWI5MGUtYWI1MzI4MDJlOTdmIiwiY2xpZW50X2lkIjoiRW5naW5lZXJXZWJBcHAifQ.eSDSU6Gy1gkKyE3_q1U2KA5A7oz3AZtP1rNxiNc9exwFwuPccRORaub6q01JxHjvzHv9UgSkrmfgdTebA1x_Wk1E5zIXmiP9eAZQmw9UfVeMHk2CauGj9x1Lgm4zH-1Ysb8h1FZ6oaJOxOgHXNh0oQ7i9Axg65844bRGD7ejWrPLLdab3p5-Eenn-XAD1B0A5FvdWw3XFiaXha1nK7kA0LltHKF-JLv_WsofqYd6rg7ti5YbOBTCEFyWJHb6DaRDRKjz4lR7p-mcnpaC6daolCuFMRFUDQik2i0xRC6SuWxDUmGaZ4IuEsJsGxlQ4t7If_1wYLexjCNxHLUX0c9SPg";
        //校验jwt令牌
        Jwt jwt = JwtHelper.decodeAndVerify(jwtString, new RsaVerifier(publickey));
        //拿到jwt令牌中自定义的内容
        String claims = jwt.getClaims();
        System.out.println(claims);
    }
}
