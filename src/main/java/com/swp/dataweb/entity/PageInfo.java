package com.swp.dataweb.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class PageInfo<T> extends BaseEntity {

    private int totalCount;//总记录数
    private int currentPage;//当前页码
    private int pageSize;//每页显示条数

}
