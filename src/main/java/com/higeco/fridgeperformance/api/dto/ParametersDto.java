package com.higeco.fridgeperformance.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParametersDto {
    private String aggregation;
    private String bucket;
    private String interval;
    private String key;
    private String node;
    private String unit;
    private String variable;
}