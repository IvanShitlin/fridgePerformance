package com.higeco.fridgeperformance.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class VariablesDto {
    private List<ItemDto> items;
}
