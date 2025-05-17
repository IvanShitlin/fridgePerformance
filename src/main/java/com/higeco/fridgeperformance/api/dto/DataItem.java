package com.higeco.fridgeperformance.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class DataItem {
    String node;
    List<List<String>> items;
}
