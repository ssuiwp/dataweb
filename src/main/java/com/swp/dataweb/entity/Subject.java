package com.swp.dataweb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * 课题组，每个课题组下有多个form
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("subject")
public class Subject extends BaseEntity {
    //课题组编号
//    @JSONField(serializeUsing= ToStringSerializer.class)
    @TableId(type = IdType.AUTO)
    private Long id;
    //课题组名称
    @JsonProperty("name")
    @NotBlank(message = "课题名为空")
    private String subjectName;
    //研究类型
    @JsonProperty("type")
    @NotBlank(message = "课题类型为空")
    private String subjectType;
    //课题牵头单位
    @JsonProperty("unit")
    @NotBlank(message = "研究单位为空")
    private String unit;
    //课题负责人
    @JsonProperty("owner")
    @NotBlank(message = "未填写负责人")
    private String owner;
//    //登记日期
//    private String createTime;
    //目前状态
    @JsonProperty("state")
    private String state;
    //备注
    @JsonProperty("desc")
    private String postscript;

    private Long userId;
}
