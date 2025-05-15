package com.higeco.fridgeperformance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@SpringBootApplication
public class FridgePerformanceApplication {

    @Autowired
    private PerformanceService performanceService;

    public static void main(String[] args) {
        SpringApplication.run(FridgePerformanceApplication.class, args);
    }

    @GetMapping(path = "/get", produces = "application/json")
    public Map<String, Double> get(@RequestParam String from, @RequestParam String to) {
        return performanceService.performance(from, to);
    }

}
