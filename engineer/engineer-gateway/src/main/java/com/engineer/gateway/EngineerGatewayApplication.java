package com.engineer.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author Lemon
 * @date 2019/9/8 17:55
 */
@EnableZuulProxy
@SpringCloudApplication
public class EngineerGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(EngineerGatewayApplication.class, args);
    }
}
