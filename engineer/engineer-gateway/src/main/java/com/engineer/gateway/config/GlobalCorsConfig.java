package com.engineer.gateway.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author Lemon
 * @date 2019/6/24 16:43
 */
@Configuration
public class GlobalCorsConfig {
   @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter(){
        // 1.添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        // 1）允许的域，不要写*，否则cookie就无法使用了
        config.addAllowedOrigin("http://api.engineering.com:9000");
        config.addAllowedOrigin("http://www.engineering.com:9000");
        // 2）是否发送cookie信息
        config.setAllowCredentials(true);
        // 3）允许的请求方式
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        // 4）允许的头信息
        config.addAllowedHeader("*");
        // 5）有效时长
        config.setMaxAge(3600L);

        // 2.添加映射路径，拦截一切请求
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

       FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(configSource));
       bean.setOrder(Ordered.HIGHEST_PRECEDENCE);

        // 3.返回新的CorsFilter
        return bean;
    }
}
