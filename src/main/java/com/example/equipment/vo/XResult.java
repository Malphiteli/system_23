package com.example.equipment.vo;

import lombok.Data;

@Data
public class XResult {
    private boolean code;
    private Object message;
    private Integer status = 200;
}
