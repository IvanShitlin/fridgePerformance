package com.higeco.fridgeperformance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableFeignClients
@EnableJpaRepositories(basePackages =  "com.higeco.fridgeperformance.dao")
@SpringBootApplication
public class FridgePerformanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FridgePerformanceApplication.class, args);
    }

}
