package com.swp.dataweb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("user")
public class User extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private BigInteger id;//用户id
    private String username;//用户名
    private String password;//用户密码
    private String email;//用户邮箱
    private String iphone;//用户电话

}
