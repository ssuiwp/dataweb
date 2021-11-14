package com.swp.dataweb.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TData extends BaseEntity {


    private Long id;
    private Form form;
    private Item multiItem;
    private Long formId;
    private Long itemId;
    private String data;
    private String creator;
    private String createTime;

}
