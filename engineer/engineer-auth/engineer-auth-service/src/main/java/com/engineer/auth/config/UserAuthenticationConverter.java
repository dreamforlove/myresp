package com.engineer.auth.config;

import com.engineer.auth.service.EngineerUserDetailsService;
import com.engineer.auth.service.UserJwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Lemon
 * @date 2019/9/10 18:34
 */
@Component
public class UserAuthenticationConverter extends DefaultUserAuthenticationConverter {

    @Autowired
    private EngineerUserDetailsService userDetailsService;

    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        LinkedHashMap response = new LinkedHashMap<>();
        String name = authentication.getName();
        response.put("userAccount", name);

        Object principal = authentication.getPrincipal();
        UserJwt userJwt = null;
        if (principal instanceof UserJwt) {
            userJwt = (UserJwt) principal;
        } else {
            // refresh_token默认不去调用userDetailService获取信息，这里我们手动去调用，得到UserJwt
            UserDetails userDetails = userDetailsService.loadUserByUsername(name);
            userJwt = (UserJwt) userDetails;
        }
        response.put("userId", userJwt.getUserId().toString());
        response.put("userName", userJwt.getUserName());
        response.put("avatarPath", userJwt.getAvatarPath());
        response.put("menu", userJwt.getMenu());
        if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()){
            response.put("authorities", AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
        }
        return response;
    }
}
