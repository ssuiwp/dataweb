package com.swp.dataweb.entity;

import lombok.Data;

import java.util.List;

@Data
public class FormQuery implements Query {

    //表单id
    private List<Long> formIds;
    //所属课题名
    private List<String> subjectNames;
    //表单名称
    private List<String> formNames;
    //所属课题组id
    private List<Long> subjectIds;
    //创建人
    private List<String> creators;
}
