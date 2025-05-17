package com.higeco.fridgeperformance.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Label{
    @JsonProperty("default")
    public String mydefault;
}