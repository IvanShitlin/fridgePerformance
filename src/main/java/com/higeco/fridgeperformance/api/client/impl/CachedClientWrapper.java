package com.higeco.fridgeperformance.api.client.impl;

import com.higeco.fridgeperformance.api.client.ExternalClientWrapper;
import com.higeco.fridgeperformance.api.dto.Item;
import com.higeco.fridgeperformance.dao.TemperatureSetpoint;
import com.higeco.fridgeperformance.dao.TemperatureSetpointRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Slf4j
@Component
@RequiredArgsConstructor
public class CachedClientWrapper implements ExternalClientWrapper {

    private final ExternalClientWrapper basicClientWrapper;

    private final TemperatureSetpointRepository temperatureSetpointRepository;

    @Override
    public List<Item> getRootItems() {
        return basicClientWrapper.getRootItems();
    }

    @Override
    public Integer getItemTemperatureSetpoint(String itemId) {
        return temperatureSetpointRepository.findByFridgeId(itemId)
                .map(TemperatureSetpoint::getTemperature)
                .orElseGet(fetchAndCacheTemperatureSetpoint(itemId));
    }

    private Supplier<Integer> fetchAndCacheTemperatureSetpoint(String itemId) {
        return () -> {
            log.info("Fetching temperature setpoint for item {}", itemId);
            Integer setpoint = basicClientWrapper.getItemTemperatureSetpoint(itemId);
            temperatureSetpointRepository.save(TemperatureSetpoint.builder()
                    .fridgeId(itemId)
                    .temperature(setpoint)
                    .build());
            return setpoint;
        };
    }

    @Override
    public Map<String, List<Double>> getItemTemperature(Collection<String> itemIds, String from, String to) {
        return basicClientWrapper.getItemTemperature(itemIds, from, to);
    }

}
