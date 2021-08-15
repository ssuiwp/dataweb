package com.swp.dataweb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 课题组，每个课题组下有多个form
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Subject extends BaseEntity {
    //课题组编号
    private long id;
    //课题组名称
    @JsonProperty("name")
    private String name;
    //研究类型
    @JsonProperty("type")
    private String type;
    //课题牵头单位
    @JsonProperty("unit")
    private String unit;
    //课题负责人
    @JsonProperty("owner")
    private String owner;
    //登记日期
    private String createTime;
    //目前状态
    @JsonProperty("state")
    private String state;
    //备注
    @JsonProperty("desc")
    private String postscript;

}
