package com.swp.dataweb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * 问项组：多个问项组成了一个表单，一个问项可以属于无数个表单
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("item")
public class Item extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long id;//问项id
    @NotBlank(message = "问项名称不能为空")
    @JsonProperty("name")
    private String itemName;//问项名称
    @NotBlank(message = "问项标题不能为空")
    private String title;//问项标题
    @NotBlank(message = "问项选项不能为空")
    private String options;//问项选项(多选的时候中间用,隔开)
    //（类型：0数字，1单选，2多选，3文字，4日期，5时间，6其他）
    @NotBlank(message = "问项选项不能为空")
    private String type;//问项类型（一个类型下可以接受多个选项）

    private String creator;

    private Long userId;
}
