package com.example.equipment.controller;

import com.example.equipment.entity.EquipmentInfoEntity;
import com.example.equipment.service.IEquipmentInfoService;
import com.example.equipment.vo.XResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/equipment/info/")
@CrossOrigin
@Slf4j
public class EquipmentInfoController {
    @Autowired
    private IEquipmentInfoService equipmentInfoServiceimpl;

    @PostMapping("/add")
    public XResult register(EquipmentInfoEntity entity){
        XResult result = equipmentInfoServiceimpl.addInfo(entity);
        return result;
    }
    @PostMapping("/update")
    public XResult update(EquipmentInfoEntity entity){
        XResult result = equipmentInfoServiceimpl.updateInfo(entity);
        return result;
    }

    @GetMapping("/list")
    public XResult list(EquipmentInfoEntity entity){
        XResult result = equipmentInfoServiceimpl.lists(entity);
        return result;
    }

    @GetMapping("/delete")
    public XResult delete(Long id){
        XResult result = equipmentInfoServiceimpl.deleteInfo(id);
        return result;
    }

    @GetMapping("/maintenance")
    public XResult maintenance(EquipmentInfoEntity entity){
        XResult result = equipmentInfoServiceimpl.maintenance(entity);
        return result;
    }

    @GetMapping("/changeStatus")
    public XResult changeStatus(Long id,Integer status){
        XResult result = equipmentInfoServiceimpl.changeStatus(id,status);
        return result;
    }

    @GetMapping("/versionUpgrade")
    public XResult versionUpgrade(Long id){
        XResult result = equipmentInfoServiceimpl.versionUpgrade(id);
        return result;
    }

    @GetMapping("/noSubregionList")
    public XResult noSubregionList(){
        XResult result = equipmentInfoServiceimpl.noSubregionList();
        return result;
    }

    @GetMapping("/addSubregion")
    public XResult addSubregion(String subregionName,String equipmentCode){
        XResult result = equipmentInfoServiceimpl.addSubregion(subregionName,equipmentCode);
        return result;
    }

    @GetMapping("/subregionEquipmentList")
    public XResult subregionEquipmentList(String subregionName,String type){
        XResult result = equipmentInfoServiceimpl.subregionEquipmentList(subregionName,type);
        return result;
    }

    @GetMapping("/replace")
    public XResult replace(String equipmentCode,String replaceEquipment){
        XResult result = equipmentInfoServiceimpl.replace(equipmentCode,replaceEquipment);
        return result;
    }
}

