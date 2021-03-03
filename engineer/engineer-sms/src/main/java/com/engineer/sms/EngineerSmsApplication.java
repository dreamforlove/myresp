package com.engineer.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Lemon
 * @date 2020/4/10 18:33
 */
@EnableDiscoveryClient
@SpringBootApplication
public class EngineerSmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(EngineerSmsApplication.class, args);
    }
}
