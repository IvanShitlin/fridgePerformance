package com.higeco.fridgeperformance.service;

import com.higeco.fridgeperformance.api.client.ExternalClientWrapper;
import com.higeco.fridgeperformance.api.dto.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PerformanceService {

    private static final double TOLERANCE_THRESHOLD = 2.0;

    private final ExternalClientWrapper cachedClientWrapper;

    private final DateValidator dateValidator;

    public Map<String, Double> performance(String from, String to) {
        dateValidator.validate(from, to);

        var fridgeToSetpointMap = cachedClientWrapper.getRootItems()
                .stream()
                .filter(item -> item.getTags().contains("refrigerator"))
                .collect(Collectors.toMap(
                        Item::getId,
                        item -> cachedClientWrapper.getItemTemperatureSetpoint(item.getId())
                ));

        var fridgeToTemperatureArrayMap = cachedClientWrapper.getItemTemperature(fridgeToSetpointMap.keySet(), from, to);

        var resultMap = new HashMap<String, Double>();
        for (var fridgeToTempArray : fridgeToTemperatureArrayMap.entrySet()) {

            var fridgeItem = fridgeToTempArray.getKey();
            var setpoint = fridgeToSetpointMap.get(fridgeItem);
            var temperatureArray = fridgeToTempArray.getValue();
            long goodCount = temperatureArray.stream()
                    .filter(isWithinSetpointRange(setpoint))
                    .count();
            double performance = goodCount * 100.0 / temperatureArray.size();

            resultMap.put(fridgeItem, performance);
        }

        return resultMap;
    }

    private static Predicate<Double> isWithinSetpointRange(Integer setpoint) {
        return t -> Math.abs(t - setpoint) <= TOLERANCE_THRESHOLD;
    }

}
