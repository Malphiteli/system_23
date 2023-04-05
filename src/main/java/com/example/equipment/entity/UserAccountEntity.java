package com.example.equipment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_account")
public class UserAccountEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String userName;

    private String name;


    private String sex;
    private Integer age;
    /**
     * 个性签名
     * */
    private String personal;
    private String phone;
    private String address;
    private String marital;

    private String password;

    private Integer status;

    private String role;

    private String createUser;
}
