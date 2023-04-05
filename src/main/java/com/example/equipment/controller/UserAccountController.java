package com.example.equipment.controller;

import com.example.equipment.base.Items;
import com.example.equipment.base.Menus;
import com.example.equipment.entity.UserAccountEntity;
import com.example.equipment.service.IUserAccountService;
import com.example.equipment.vo.XResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/equipment/user/")
@CrossOrigin
@Slf4j
public class UserAccountController {
    @Autowired
    private IUserAccountService userAccountServiceimpl;
    @GetMapping("/register")
    public XResult register(UserAccountEntity userAccount){
        log.info(userAccount.getUserName());
        userAccount.setRole("普通用户");
        userAccount.setStatus(0);
        XResult register = userAccountServiceimpl.register(userAccount);
        return register;
    }

    @GetMapping("/login")
    public XResult login(UserAccountEntity userAccount){
        log.info(userAccount.getUserName());
        XResult login = userAccountServiceimpl.login(userAccount);
        return login;
    }

    @GetMapping("/update")
    public XResult update(UserAccountEntity userAccount){
        XResult update = userAccountServiceimpl.update(userAccount);
        return update;
    }
    @PostMapping("/updatePersonal")
    public XResult updatePersonal(UserAccountEntity userAccount){
        XResult update = userAccountServiceimpl.updatePersonal(userAccount);
        return update;
    }
    @GetMapping("/delete")
    public XResult delete(String userName){
        XResult update = userAccountServiceimpl.delete(userName);
        return update;
    }

    @GetMapping("/updatePasswd")
    public XResult updatePasswd(HttpServletRequest request,String oldPasswd,String newPasswd){
        XResult update = userAccountServiceimpl.updatePasswd(request,oldPasswd,newPasswd);
        return update;
    }

    @GetMapping("/get")
    public XResult get(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        XResult list = userAccountServiceimpl.get(authorization);
        return list;
    }

    @GetMapping("/list")
    public XResult list(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        XResult list = userAccountServiceimpl.list(authorization);
        return list;
    }

    @GetMapping("/getUserInfo")
    public Menus getUserInfo(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        Menus menus = new Menus();
        menus.setLongTitle("设备管理系统");
        menus.setLittleTitle("设备管理");

        Items equipmentMaintenance = new Items();
        equipmentMaintenance.setIconName("home");
        equipmentMaintenance.setName("设备维护信息");
        equipmentMaintenance.setRouterName("/equipmentMaintenance");
        equipmentMaintenance.setDisabled(false);

        Items equipmentInfo = new Items();
        equipmentInfo.setIconName("equipmentInfo");
        equipmentInfo.setName("设备基础信息");
        equipmentInfo.setRouterName("/equipmentInfo");
        equipmentInfo.setDisabled(false);

        Items equipmentSubregion = new Items();
        equipmentSubregion.setIconName("equipmentSubregion");
        equipmentSubregion.setName("设备分区信息");
        equipmentSubregion.setRouterName("/equipmentSubregion");
        equipmentSubregion.setDisabled(false);

        Items equipmentVersion = new Items();
        equipmentVersion.setIconName("equipmentVersion");
        equipmentVersion.setName("设备版本信息");
        equipmentVersion.setRouterName("/equipmentVersion");
        equipmentVersion.setDisabled(false);

        Items equipmentWorkLog = new Items();
        equipmentWorkLog.setIconName("equipmentWorkLog");
        equipmentWorkLog.setName("设备工作信息");
        equipmentWorkLog.setRouterName("/equipmentWorkLog");
        equipmentWorkLog.setDisabled(false);

        Items equipmentType = new Items();
        equipmentType.setIconName("equipmentType");
        equipmentType.setName("设备类型信息");
        equipmentType.setRouterName("/equipmentType");
        equipmentType.setDisabled(false);

        Items personalInfo = new Items();
        personalInfo.setIconName("personalInfo");
        personalInfo.setName("个人信息维护");
        personalInfo.setRouterName("/personalInfo");
        personalInfo.setDisabled(false);

        Items update = new Items();
        update.setIconName("update");
        update.setName("密码修改");
        update.setRouterName("/update");
        update.setDisabled(false);

        List list = new ArrayList();
        list.add(equipmentMaintenance);
        list.add(equipmentInfo);
        list.add(equipmentSubregion);
        list.add(equipmentVersion);
        list.add(equipmentWorkLog);
        list.add(equipmentType);
        list.add(personalInfo);
        list.add(update);
        menus.setItems(list);
        return menus;
    }
}

