package com.engineer.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Lemon
 * @date 2019/9/6 18:44
 */
@EnableEurekaServer
@SpringBootApplication
public class EngineerRegistryApplication {
    public static void main(String[] args) {
        SpringApplication.run(EngineerRegistryApplication.class, args);
    }
}
