package com.engineer.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Lemon
 * @date 2019/9/9 15:50
 */
@Data
@Component
@ConfigurationProperties(prefix = "engineer.auth")
public class AuthProperties {
    private long tokenValiditySeconds;
    private String clientId;
    private String clientSecret;
    private String cookieDomain;
    private int cookieMaxAge;
}
