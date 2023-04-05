package com.example.equipment.base;

import lombok.Data;

import java.util.List;

@Data
public class Items {
    private String iconName;
    private String name;
    private String routerName;
    private boolean disabled;
    private List<Items> submenu;
}
