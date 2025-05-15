package com.higeco.fridgeperformance;

import lombok.Data;

import java.util.List;

@Data
public class DatapointDto {
    List<DataItem> items;
}
