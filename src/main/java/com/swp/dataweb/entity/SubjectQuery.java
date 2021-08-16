package com.swp.dataweb.entity;

import lombok.Data;

import java.util.List;

@Data
public class SubjectQuery extends Query {

    //课题id
    private List<Long> subjectIds;

    //课题名称
    private List<String> subjectNames;
    //课题类型
    private List<String> subjectType;
    //课题单位
    private List<String> subjectUnit;
    //创建人
    private List<String> owners;
}
