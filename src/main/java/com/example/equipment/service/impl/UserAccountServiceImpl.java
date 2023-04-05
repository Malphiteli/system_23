package com.example.equipment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.equipment.base.Items;
import com.example.equipment.base.Menus;
import com.example.equipment.dao.UserAccountMapper;
import com.example.equipment.entity.UserAccountEntity;
import com.example.equipment.service.IUserAccountService;
import com.example.equipment.vo.UserAccountResponse;
import com.example.equipment.vo.XResult;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccountEntity> implements IUserAccountService {
    @Autowired
    private UserAccountMapper mapper;

    @Override
    public XResult login(UserAccountEntity userAccount) {
        XResult result = new XResult();
        result.setCode(true);
        result.setMessage(userAccount.getUserName());
        Map map = new HashMap<>();
        map.put("user_name", userAccount.getUserName());
        List<UserAccountEntity> list = mapper.selectByMap(map);
        if (ObjectUtils.isEmpty(list)) {
            result.setCode(false);
            result.setMessage("用户不存在");
        } else {
            if(list.get(0).getStatus()!=0){
                result.setCode(false);
                result.setMessage("当前账号已经被冻结");
            }
            if (!list.get(0).getPassword().equals(DigestUtils.md5Hex(userAccount.getPassword()))) {
                result.setCode(false);
                result.setMessage("密码不正确");
            }
        }
        return result;
    }

    @Override
    public XResult register(UserAccountEntity userAccount) {
        XResult result = new XResult();
        result.setCode(true);
        result.setMessage("注册成功");
        Map map = new HashMap<>();
        map.put("user_name", userAccount.getUserName());
        List list = mapper.selectByMap(map);
        if (ObjectUtils.isEmpty(list)) {
            String encode = DigestUtils.md5Hex(userAccount.getPassword());
            userAccount.setPassword(encode);
            this.save(userAccount);
            return result;
        } else {
            result.setCode(false);
            result.setMessage("当前用户已经存在");
        }
        return result;
    }

    @Override
    public XResult update(UserAccountEntity userAccount) {
        XResult result = new XResult();
        result.setCode(true);
        result.setMessage("已冻结");
        Map map = new HashMap();
        map.put("name",userAccount.getUserName());
        List<UserAccountEntity> list = mapper.selectByMap(map);
        UserAccountEntity userAccount1 = list.get(0);
        userAccount1.setStatus(1);
        this.updateById(userAccount1);
        return result;
    }

    @Override
    public XResult updatePersonal(UserAccountEntity userAccount) {
        XResult result = new XResult();
        result.setCode(true);
        result.setMessage("已修改");
        this.updateById(userAccount);
        return result;
    }

    @Override
    public XResult delete(String userName) {
        XResult result = new XResult();
        result.setCode(true);
        result.setMessage("删除成功");
        Map map = new HashMap<>();
        map.put("user_name", userName);
        List list = mapper.selectByMap(map);
        if (ObjectUtils.isEmpty(list)) {
            result.setCode(false);
            result.setMessage("当前用户不存在");
        } else {
            this.removeByMap(map);
        }
        return result;
    }

    @Override
    public XResult updatePasswd(HttpServletRequest request, String oldPasswd, String newPasswd) {
        String authorization = request.getHeader("Authorization");
        XResult result = new XResult();
        result.setCode(true);
        Map map = new HashMap<>();
        map.put("user_name", authorization);
        UserAccountEntity entity = (UserAccountEntity) mapper.selectByMap(map).get(0);
        String oldPasswdEncode = DigestUtils.md5Hex(oldPasswd);
        if(!oldPasswdEncode.equals(entity.getPassword())){
            result.setCode(false);
            result.setMessage("密码不正确，拒绝修改");
        }else{
            String newPasswdEncode = DigestUtils.md5Hex(newPasswd);
            entity.setPassword(newPasswdEncode);
            this.updateById(entity);
            result.setMessage("密码已修改");
        }
        return result;
    }

    @Override
    public XResult list(String createUser) {
        XResult result = new XResult();
        result.setCode(true);
        Map map = new HashMap<>();
        map.put("create_user", createUser);
        List<UserAccountEntity> list = mapper.selectByMap(map);
        List<UserAccountResponse> r = new ArrayList<>();
        for (UserAccountEntity entity : list) {
            UserAccountResponse userAccountResponse = new UserAccountResponse();
            userAccountResponse.setLname(entity.getName());
            userAccountResponse.setLusername(entity.getUserName());
            r.add(userAccountResponse);
        }
        result.setMessage(r);
        return result;
    }

    @Override
    public XResult get(String userName) {
        XResult result = new XResult();
        result.setCode(true);
        Map map = new HashMap<>();
        map.put("user_name", userName);
        UserAccountEntity entity = (UserAccountEntity) mapper.selectByMap(map).get(0);
        result.setMessage(entity);
        return result;
    }

    @Override
    public Menus getMenus(String authorization) {
        Menus menus = new Menus();
        menus.setLongTitle("智能答疑");
        menus.setLittleTitle("答疑");
        List list = new ArrayList();

        Items index = new Items();
        index.setIconName("home");
        index.setName("智能答疑");
        index.setRouterName("/index");
        index.setDisabled(false);

        list.add(index);
        //游客
        if(ObjectUtils.isEmpty(authorization)){
            menus.setItems(list);
            return menus;
        }else{
            Map map = new HashMap();
            map.put("user_name",authorization);
            List<UserAccountEntity> list1 = mapper.selectByMap(map);
            if(ObjectUtils.isEmpty(list1)){
                menus.setItems(list);
                return menus;
            }else{
                if("学生".equals(list1.get(0).getRole())){
                    Items answer = new Items();
                    answer.setIconName("answer");
                    answer.setName("答案列表");
                    answer.setRouterName("/answer");
                    answer.setDisabled(false);
                    list.add(answer);
                    menus.setItems(list);
                    return menus;
                }else{
                    Items users = new Items();
                    users.setIconName("users");
                    users.setName("用户列表");
                    users.setRouterName("/users");
                    users.setDisabled(false);

                    Items question = new Items();
                    question.setIconName("question");
                    question.setName("问题列表");
                    question.setRouterName("/question");
                    question.setDisabled(false);

                    list.add(users);
                    list.add(question);
                    menus.setItems(list);
                    return menus;
                }
            }
        }
    }
}
