package com.swp.dataweb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigInteger;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserDesc extends BaseEntity{
    private BigInteger id;//用户id
    private String username;//用户名
    private String email;//用户邮箱
    private String iphone;//用户电话
}
