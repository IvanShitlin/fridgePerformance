package com.higeco.fridgeperformance;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PerformanceService {

    private final HigecoApiClient higecoApiClient;

    public Map<String, Double> performance(String from, String to) {
        var items = higecoApiClient.getNodes().getItems();

        var friges = items.stream()
                .filter(item -> item.getTags().contains("refrigerator"))
                .map(Item::getId)
                .collect(Collectors.toList());

        var map = new HashMap<String, Integer>();
        for (var item : friges) {
            var res = higecoApiClient.getVariables(item);
            map.put(item, res);
        }

        Map<String, Double> perfMap = new HashMap<>();
        for (var item : friges) {
            var res = higecoApiClient.getDatapoints(
                    item,
                    from,
                    to
            );

            var good = 0;
            for (var t : res.items.get(0).items) {
                if (t.get(3) == null) continue;
                if (Math.abs(Double.parseDouble(t.get(3)) - map.get(item)) < 2) {
                    good++;
                }
            }
            var perf = good * 100.0 / res.items.get(0).items.size();
            perfMap.put(item, perf);
        }

        return perfMap;
    }

}
