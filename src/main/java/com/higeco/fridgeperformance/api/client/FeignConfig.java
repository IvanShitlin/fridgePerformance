package com.higeco.fridgeperformance.api.client;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Value("${higeco.api.username}")
    private String username;

    @Value("${higeco.api.password}")
    private String password;

    @Bean
    public BasicAuthRequestInterceptor basicAuthInterceptor() {
        return new BasicAuthRequestInterceptor(username, password);
    }
}