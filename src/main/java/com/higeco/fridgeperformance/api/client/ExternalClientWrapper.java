package com.higeco.fridgeperformance.api.client;

import com.higeco.fridgeperformance.api.dto.Item;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface ExternalClientWrapper {

    List<Item> getRootItems();

    Integer getItemTemperatureSetpoint(String itemId);

    Map<String, List<Double>> getItemTemperature(Collection<String> itemIds, String from, String to);
}
