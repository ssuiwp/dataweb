package com.swp.dataweb.entity.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormQuery implements Serializable {

    //表单id
    private Long id;
    //所属课题id //进入表单管理直接查询该用户所有的表单 并传递表单名与表单id
    //同时查询该用户所有问项, 添加表单需要与问项简历关联
    private Long subjectId;
    //表单名称
    private String formName;

    private int size;

    private int current;
}
