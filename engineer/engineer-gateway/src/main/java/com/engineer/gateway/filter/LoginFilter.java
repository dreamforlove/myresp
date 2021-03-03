package com.engineer.gateway.filter;

import com.engineer.gateway.service.AuthService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import jdk.nashorn.internal.objects.NativeInt16Array;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Lemon
 * @date 2020/3/7 22:36
 */
@Component
public class LoginFilter extends ZuulFilter {

    private static final String LOGIN_URL = "/api/auth/login";
    private static final String USERINFO_URL = "/api/auth/userInfo";
    private static final String LOGOUT_URL = "/api/auth/logout";
    private static final String CHECK_USER_DATA_URL = "/api/ucenter/user/check";
    private static final String SEND_CODE_URL = "/api/ucenter/user/code";
    private static final String REGISTER_URL = "/api/ucenter/user/register";
    private static final String CHANGE_PASSWORD_URL = "/api/ucenter/user/change/password";

    @Autowired
    AuthService authService;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String token = authService.getTokenFromCookie(request);
        System.out.println(token);
        String url = request.getRequestURI();
        System.out.println(url);
        boolean flag = StringUtils.equals(LOGIN_URL, url) || StringUtils.equals(USERINFO_URL, url)
                || StringUtils.equals(LOGOUT_URL, url) || StringUtils.equals(REGISTER_URL, url)
                || StringUtils.equals(CHECK_USER_DATA_URL, url) || StringUtils.equals(SEND_CODE_URL, url)
                || StringUtils.equals(CHANGE_PASSWORD_URL, url);
        System.out.println(flag);
        if (!flag) {
            System.out.println("开始检查token");
            if (token == null) {
                System.out.println("token为空");
                accessDenied();
                return null;
            }
            long expire = authService.getExpire(token);
            if (expire <= 0) {
                System.out.println("token已过期");
                accessDenied();
                return null;
            }
            String jwt = authService.getJwtFromHeader(request);
            System.out.println(jwt);
            if (jwt == null) {
                System.out.println("jwt令牌为空");
                accessDenied();
                return null;
            }
            authService.extendExpire(token);
        }
        return null;
    }

    /**
     * 拒绝访问
     */
    private void accessDenied() {
        System.out.println("拒绝访问");
        RequestContext requestContext = RequestContext.getCurrentContext();
        // 拒绝访问
        requestContext.setSendZuulResponse(false);
        // ResponseEntity<Void> responseEntity = new ResponseEntity<Void>(HttpStatus.SC_UNAUTHORIZED);
        requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
        HttpServletResponse response = requestContext.getResponse();
        response.setContentType("application/json;charset=utf-8");
    }
}
