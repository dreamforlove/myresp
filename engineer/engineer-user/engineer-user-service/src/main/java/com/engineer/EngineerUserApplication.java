package com.engineer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Lemon
 * @date 2019/9/7 17:18
 */
@MapperScan("com.engineer.user.mapper")
@EnableAsync
@EnableDiscoveryClient
@SpringBootApplication
public class EngineerUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(EngineerUserApplication.class, args);
    }
}
