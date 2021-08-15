package com.swp.dataweb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 问项组：多个问项组成了一个表单，一个问项可以属于无数个表单
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MultiItem extends BaseEntity {

    //    @JsonProperty("")
    private long id;//问项id
    //    @JsonProperty("")
    private String name;//问项名称
    //    @JsonProperty("")
    private String title;//问项标题
    //    @JsonProperty("")
    private String options;//问项选项（选项：0数字，1单选，2多选，3文字，4日期，5时间，6其他）
    //    @JsonProperty("")
    private String type;//问项类型（一个类型下可以接受多个选项）

    private String createTime;
    //    @JsonProperty("")
    private String postscript;//问项描述


}
