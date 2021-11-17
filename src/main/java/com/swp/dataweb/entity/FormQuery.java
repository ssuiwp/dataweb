package com.swp.dataweb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormQuery implements Serializable {

    //表单id
    private Long id;
    //所属课题id
    private Long subjectId;
    //表单名称
    private String formName;
    //创建人
    private String creator;

    //课题名称
    @JsonProperty("name")
    private String subjectName;
    //课题类型
    @JsonProperty("type")
    private String subjectType;
    //课题单位
    @JsonProperty("unit")
    private String unit;
    //创建人
    @JsonProperty("owner")
    private String owners;
    @JsonProperty("size")
    private int size;
    @JsonProperty("current")
    private int current;
}
