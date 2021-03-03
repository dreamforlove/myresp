package com.engineer.auth.service;

import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

/**
 * @author Lemon
 * @date 2019/9/10 18:38
 */
@Data
@ToString
public class UserJwt extends User {

    private Long userId;
    private String userName;
    private String avatarPath;
    private List<String> menu;


    UserJwt(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
