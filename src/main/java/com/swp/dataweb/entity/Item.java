package com.swp.dataweb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 问项组：多个问项组成了一个表单，一个问项可以属于无数个表单
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("item")
public class Item extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long id;//问项id
    private String itemName;//问项名称
    private String title;//问项标题
    private String options;//问项选项（选项：0数字，1单选，2多选，3文字，4日期，5时间，6其他）
    private String type;//问项类型（一个类型下可以接受多个选项）
    private String postscript;//问项描述
    private String creator;

}
