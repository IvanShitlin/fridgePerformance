package com.higeco.fridgeperformance;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Item{
    public Attributes attributes;
    public ArrayList<String> children;
    public String id;
    public Label label;
    public Policies policies;
    public ArrayList<String> tags;
    public String tenant;
}