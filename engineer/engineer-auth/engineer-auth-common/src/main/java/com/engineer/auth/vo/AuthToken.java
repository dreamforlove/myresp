package com.engineer.auth.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Lemon
 * @date 2019/9/9 15:40
 */
@Data
@ToString
@NoArgsConstructor
public class AuthToken {
    /**
     * jwt令牌
     */
    String accessToken;
    /**
     * 刷新token
     */
    String refreshToken;
    /**
     * 访问token 短令牌，用户身份令牌
     */
    String jti;
}
