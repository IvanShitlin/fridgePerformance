package com.higeco.fridgeperformance;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Data
public class VariablesDto {
    private List<ItemDto> items;
}
