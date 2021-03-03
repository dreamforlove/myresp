package com.engineer.auth.service;

import com.engineer.auth.vo.AuthToken;

/**
 * @author Lemon
 * @date 2019/9/9 15:22
 */
public interface AuthService {
    /**
     * 用户登录
     * @param userAccount
     * @param password
     * @return
     */
    String login(String userAccount, String password);

    /**
     * 获取用户jwt令牌
     * @param token
     * @return
     */
    AuthToken getUserInfo(String token);

    /**
     * 删除redis中的用户令牌
     * @param token
     */
    void delToken(String token);
}
