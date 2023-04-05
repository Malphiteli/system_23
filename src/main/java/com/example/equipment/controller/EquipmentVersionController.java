package com.example.equipment.controller;

import com.example.equipment.entity.EquipmentVersionEntity;
import com.example.equipment.service.IEquipmentVersionService;
import com.example.equipment.vo.XResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/equipment/version/")
@CrossOrigin
@Slf4j
public class EquipmentVersionController {
    @Autowired
    private IEquipmentVersionService equipmentVersionServiceimpl;

    @PostMapping("/add")
    public XResult register(EquipmentVersionEntity entity){
        XResult result = equipmentVersionServiceimpl.addInfo(entity);
        return result;
    }
    @PostMapping("/update")
    public XResult update(EquipmentVersionEntity entity){
        XResult result = equipmentVersionServiceimpl.updateInfo(entity);
        return result;
    }

    @GetMapping("/list")
    public XResult list(EquipmentVersionEntity entity){
        XResult result = equipmentVersionServiceimpl.lists(entity);
        return result;
    }

    @GetMapping("/delete")
    public XResult delete(Long id){
        XResult result = equipmentVersionServiceimpl.deleteInfo(id);
        return result;
    }
}

