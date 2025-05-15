package com.higeco.fridgeperformance;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Label{
    @JsonProperty("default")
    public String mydefault;
//    public String en-GB;
//    public String it-IT;
}