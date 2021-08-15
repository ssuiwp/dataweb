package com.swp.dataweb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode
public class User extends BaseEntity {

    //    @JsonProperty("id")
    private long id;//用户id
    //    @JsonProperty("id")
    private String name;//用户名
    //    @JsonProperty("id")
    private String password;//用户密码
    //    @JsonProperty("id")
    private String email;//用户邮箱
    //    @JsonProperty("id")
    private String iphone;//用户电话
    //    @JsonProperty("id")
    private String creator;//创建人

}
