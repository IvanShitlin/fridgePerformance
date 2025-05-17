package com.higeco.fridgeperformance.api.client.impl;

import com.higeco.fridgeperformance.api.client.ExternalClientWrapper;
import com.higeco.fridgeperformance.api.client.HigecoFeignClient;
import com.higeco.fridgeperformance.api.dto.DataItem;
import com.higeco.fridgeperformance.api.dto.Item;
import com.higeco.fridgeperformance.api.dto.ItemDto;
import com.higeco.fridgeperformance.api.dto.ParametersDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BasicClientWrapper implements ExternalClientWrapper {

    private static final String AGGREGATION = "avg";
    private static final String TEMPERATURE_PROBE = "temperature_probe";
    private static final String BUCKET = "PT1M";
    private static final String UNIT = "degC";
    private static final int INDEX_OF_TEMPERATURE_VALUE = 3;

    private final HigecoFeignClient higecoClient;

    @Override
    public List<Item> getRootItems() {
        return higecoClient.getNodes().getItems();
    }

    @Override
    public Integer getItemTemperatureSetpoint(String itemId) {
        var items = higecoClient.getVariables(itemId, "temperature_setpoint").getItems();
        return Optional.of(items)
                .map(List::getFirst)
                .map(ItemDto::getValue)
                .map(value -> value.get("data"))
                .map(Integer::parseInt)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public Map<String, List<Double>> getItemTemperature(Collection<String> itemIds, String from, String to) {
        var params = itemIds.stream()
                .map(itemId -> buildParameters(itemId, from, to))
                .toList();

        return higecoClient.getDatapoints(params)
                .getItems()
                .stream()
                .collect(Collectors.toMap(
                        DataItem::getNode,
                        dataItem -> dataItem.getItems()
                                .stream()
                                .map(extractTemperatureValue())
                                .toList()
                ));

    }

    private static Function<List<String>, Double> extractTemperatureValue() {
        return row -> {
            String temperatureValue = row.get(INDEX_OF_TEMPERATURE_VALUE);
            return Optional.ofNullable(temperatureValue)
                    .map(Double::parseDouble)
                    .orElse(Double.MAX_VALUE);
        };
    }

    private ParametersDto buildParameters(String itemId, String from, String to) {
        return ParametersDto.builder()
                .node(itemId)
                .aggregation(AGGREGATION)
                .key(TEMPERATURE_PROBE)
                .interval(from + "/" + to)
                .bucket(BUCKET)
                .unit(UNIT)
                .build();
    }

}
