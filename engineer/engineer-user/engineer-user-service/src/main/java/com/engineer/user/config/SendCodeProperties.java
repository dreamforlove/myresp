package com.engineer.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ASUS
 * @date 2019/7/5 16:08
 */
@Data
@ConfigurationProperties(prefix = "engineer.sms")
public class SendCodeProperties {
    private String exchange;
    private String routingKey;
    private Integer codeTimeOut;
}
