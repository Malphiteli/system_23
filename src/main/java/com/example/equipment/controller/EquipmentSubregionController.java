package com.example.equipment.controller;

import com.example.equipment.entity.EquipmentSubregionEntity;
import com.example.equipment.service.IEquipmentSubregionService;
import com.example.equipment.vo.XResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/equipment/subregion/")
@CrossOrigin
@Slf4j
public class EquipmentSubregionController {
    @Autowired
    private IEquipmentSubregionService equipmentSubregionServiceimpl;

    @PostMapping("/add")
    public XResult register(EquipmentSubregionEntity entity){
        XResult result = equipmentSubregionServiceimpl.addInfo(entity);
        return result;
    }
    @PostMapping("/update")
    public XResult update(EquipmentSubregionEntity entity){
        XResult result = equipmentSubregionServiceimpl.updateInfo(entity);
        return result;
    }

    @GetMapping("/list")
    public XResult list(EquipmentSubregionEntity entity){
        XResult result = equipmentSubregionServiceimpl.lists(entity);
        return result;
    }

    @GetMapping("/delete")
    public XResult delete(Long id){
        XResult result = equipmentSubregionServiceimpl.deleteInfo(id);
        return result;
    }
}

