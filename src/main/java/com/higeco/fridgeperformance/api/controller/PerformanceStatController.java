package com.higeco.fridgeperformance.api.controller;

import com.higeco.fridgeperformance.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PerformanceStatController {

    private final PerformanceService performanceService;

    @GetMapping(path = "/performance", produces = "application/json")
    public Map<String, Double> get(@RequestParam String from, @RequestParam String to) {
        return performanceService.performance(from, to);
    }

}
