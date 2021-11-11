package com.swp.dataweb.entity;

/**
 * 问项类型枚举
 *
 */
public enum ItemType {
    FIGURE("数字",0),
    MULTIPLE("单选",1),
    MULTISELECT("多选",2),
    CHARACTER("文字",3),
    DATE("日期",4),
    TIME("时间",5),
    ELSE("其他",6);

    private final String name;
    private final int index;
    ItemType(String itemType, int index){
        this.name = itemType;
        this.index = index;
    }
    public String getItemType(){
        return name;
    }
    public Integer getIndex(){
        return index;
    }

}
