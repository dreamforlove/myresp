package com.engineer.sms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ASUS
 * @date 2019/7/4 15:10
 */
@Data
@ConfigurationProperties(prefix = "engineer.sms")
public class SmsProperties {
    String accessKeyId;
    String accessKeySecret;
    String signName;
    String verifyCodeTemplate;
}
