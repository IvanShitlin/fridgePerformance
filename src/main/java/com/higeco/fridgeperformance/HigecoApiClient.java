package com.higeco.fridgeperformance;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.awt.*;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

@Component
public class HigecoApiClient {
    private final RestTemplate rest;
    private final String baseUrl;

    public HigecoApiClient(
            RestTemplateBuilder builder,
            @Value("${higeco.api.base-url}") String baseUrl,
            @Value("${higeco.api.username}") String username,
            @Value("${higeco.api.password}") String password) {
        this.baseUrl = baseUrl;
        this.rest = builder
            .basicAuthentication(username, password)
            .build();
    }

    public Node getNodes() {
        ResponseEntity<Node> resp = rest.getForEntity(baseUrl + "/api/v1/nodes", Node.class);
        return resp.getBody();
    }

    public Integer getVariables(String nodeId) {
        UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(baseUrl + "/api/v1/nodes/variables")
                .queryParam("node", nodeId)
                .queryParam("key", "temperature_setpoint");
        ResponseEntity<VariablesDto> resp = rest.getForEntity(uri.toUriString(), VariablesDto.class);
        return Integer.parseInt(resp.getBody().getItems().get(0).getValue().get("data"));
    }

    public DatapointDto getDatapoints(String nodeId, String from, String to) {
        UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(baseUrl + "/api/v1/nodes/variables/datapoints/query");
        var params = ParametersDto.builder()
                .node(nodeId)
                .aggregation("avg")
                .key("temperature_probe")
                .interval(from + "/" + to)
                .bucket("PT1M")
                .unit("degC")
                .build();

        ResponseEntity<DatapointDto> resp = rest.postForEntity(uri.toUriString(), Collections.singleton(params), DatapointDto.class);
        return (resp.getBody());
    }

}