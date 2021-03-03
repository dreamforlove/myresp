package com.engineer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.spring.annotation.MapperScan;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.engineer.result.mapper")
public class EngineerFinalApplication {
    public static void main(String[] args) {
        SpringApplication.run(EngineerFinalApplication.class);
    }
}
