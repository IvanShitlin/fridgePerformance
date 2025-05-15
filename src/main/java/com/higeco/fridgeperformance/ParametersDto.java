package com.higeco.fridgeperformance;

import lombok.Builder;
import lombok.Data;

@Data
    @Builder
    public class ParametersDto {
        public String aggregation;
        public String bucket;
        public String interval;
        public String key;
        public String node;
        public String unit;
        public String variable;
    }