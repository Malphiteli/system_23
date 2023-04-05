package com.example.equipment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.equipment.base.Menus;
import com.example.equipment.entity.UserAccountEntity;
import com.example.equipment.vo.XResult;

import javax.servlet.http.HttpServletRequest;

public interface IUserAccountService extends IService<UserAccountEntity> {
    XResult login(UserAccountEntity userAccount);
    XResult register(UserAccountEntity userAccount);

    XResult update(UserAccountEntity userAccount);

    XResult updatePersonal(UserAccountEntity userAccount);

    XResult delete(String userName);

    XResult updatePasswd(HttpServletRequest request, String oldPasswd, String newPasswd);

    XResult list(String createUser);

    XResult get(String userName);

    Menus getMenus(String authorization);
}
