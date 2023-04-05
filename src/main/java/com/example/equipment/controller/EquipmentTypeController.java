package com.example.equipment.controller;


import com.example.equipment.entity.EquipmentTypeEntity;
import com.example.equipment.service.IEquipmentTypeService;
import com.example.equipment.vo.XResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/equipment/type/")
@CrossOrigin
@Slf4j
public class EquipmentTypeController {
    @Autowired
    private IEquipmentTypeService equipmentTypeServiceimpl;

    @PostMapping("/add")
    public XResult register(EquipmentTypeEntity entity){
        XResult result = equipmentTypeServiceimpl.addInfo(entity);
        return result;
    }
    @PostMapping("/update")
    public XResult update(EquipmentTypeEntity entity){
        XResult result = equipmentTypeServiceimpl.updateInfo(entity);
        return result;
    }

    @GetMapping("/list")
    public XResult list(EquipmentTypeEntity entity){
        XResult result = equipmentTypeServiceimpl.lists(entity);
        return result;
    }

    @GetMapping("/delete")
    public XResult delete(Long id){
        XResult result = equipmentTypeServiceimpl.deleteInfo(id);
        return result;
    }
}

