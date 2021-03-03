package com.engineer.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Lemon
 * @date 2020/3/17 16:57
 */
@Data
@ConfigurationProperties(prefix = "engineer.worker")
public class IdWorkerProperties {
    /**
     * 当前机器id
     */
    private Long workerId;
    /**
     * 序列号
     */
    private Long dataCenterId;
}
