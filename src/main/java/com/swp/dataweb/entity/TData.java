package com.swp.dataweb.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TData extends BaseEntity {


    private long id;
    private Form form;
    private MultiItem multiItem;
    private long formId;
    private long itemId;
    private String data;
    private String creator;
    private String createTime;

}
