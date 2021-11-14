package com.swp.dataweb.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class FormMultiItemRelation extends BaseEntity {

    //表单id
    private Long formId;

    //问项id
    private List<Long> multiItemIds;

    //登记人
    private String creator;
//    关联表id
    private long id;

}
