package com.swp.dataweb.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("form")
public class Form extends BaseEntity {

    private Long id;//表单id
    private String name;//表单名称
    // 归属课题
    private Subject subject;
    // 表单下的问项
    @JsonProperty("multiItems")
    private List<Item> multiItems;
    @JsonProperty("owner")
    private String creator;//登记人
    private String created;//登记时间
    @JsonProperty("desc")
    private String postscript;//表单描述
}
