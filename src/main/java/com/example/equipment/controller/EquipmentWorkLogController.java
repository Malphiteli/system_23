package com.example.equipment.controller;

import com.example.equipment.entity.EquipmentWorkLogEntity;
import com.example.equipment.service.IEquipmentWorkLogService;
import com.example.equipment.vo.XResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/equipment/work/log/")
@CrossOrigin
@Slf4j
public class EquipmentWorkLogController {
    @Autowired
    private IEquipmentWorkLogService equipmentWorkLogServiceimpl;

    @GetMapping("/list")
    public XResult list(EquipmentWorkLogEntity entity){
        XResult result = equipmentWorkLogServiceimpl.lists(entity);
        return result;
    }

}

