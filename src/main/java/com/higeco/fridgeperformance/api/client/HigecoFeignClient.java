// src/main/java/com/higeco/fridgeperformance/client/HigecoFeignClient.java
package com.higeco.fridgeperformance.api.client;

import com.higeco.fridgeperformance.api.dto.DatapointDto;
import com.higeco.fridgeperformance.api.dto.Node;
import com.higeco.fridgeperformance.api.dto.ParametersDto;
import com.higeco.fridgeperformance.api.dto.VariablesDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
    name = "higeco-api",
    url = "${higeco.api.base-url}",
    configuration = FeignConfig.class
)
public interface HigecoFeignClient {

    @GetMapping("/api/v1/nodes")
    Node getNodes();

    @GetMapping("/api/v1/nodes/variables")
    VariablesDto getVariables(
        @RequestParam("node") String nodeId,
        @RequestParam("key") String key
    );

    @PostMapping(
        path = "/api/v1/nodes/variables/datapoints/query",
        consumes = "application/json"
    )
    DatapointDto getDatapoints(@RequestBody List<ParametersDto> params);
}