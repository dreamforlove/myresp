package com.engineer;

import com.engineer.common.interceptor.FeignClientInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author Lemon
 * @date 2019/9/6 20:36
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class EngineerAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(EngineerAuthApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }

    @Bean
    public FeignClientInterceptor getFeignClientInterceptor() {
        return new FeignClientInterceptor();
    }
}
