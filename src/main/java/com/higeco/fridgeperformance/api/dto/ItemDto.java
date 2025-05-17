package com.higeco.fridgeperformance.api.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ItemDto {
    private Map<String, String> value;
}