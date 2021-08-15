package com.swp.dataweb.entity;

import lombok.Data;

import java.util.List;

@Data
public class MultiItemQuery {

    //问项id
    private List<Long> itemIds;
    //问项名称
    private List<String> itemNames;
    //问项类型
    private List<String> types;
    //问项标题
    private List<String> titles;

}
