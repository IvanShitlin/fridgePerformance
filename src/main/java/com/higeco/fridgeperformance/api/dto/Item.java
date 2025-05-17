package com.higeco.fridgeperformance.api.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Item{
    public String id;
    public Label label;
    public ArrayList<String> tags;
}