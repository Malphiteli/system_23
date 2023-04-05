package com.example.equipment.base;

import lombok.Data;

import java.util.List;

@Data
public class Menus {
    private String longTitle;
    private String littleTitle;
    private List<Items> items;
}
