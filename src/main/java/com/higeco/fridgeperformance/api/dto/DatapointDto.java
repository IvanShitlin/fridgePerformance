package com.higeco.fridgeperformance.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class DatapointDto {
    List<DataItem> items;
}
