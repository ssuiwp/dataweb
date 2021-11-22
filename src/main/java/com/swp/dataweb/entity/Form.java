package com.swp.dataweb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("form")
public class Form extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long id;//表单id
    @NotBlank(message = "表单名称不能为空")
    @JsonProperty("name")
    private String formName;//表单名称
    // 归属课题id
    @NotBlank(message = "归属课题不能为空")
    private Long subjectId;
    private String subjectName;
    // 表单下的问项
    @JsonProperty("questions")
    @NotBlank(message = "问项不能为空")
    @TableField(exist = false)
    private List<Long> itemIds;
    @JsonProperty("owner")
    private String creator;//登记人
    private String created;//登记时间
    @JsonProperty("desc")
    private String postscript;//表单描述
}
