package com.swp.dataweb.entity;

/**
 * 问项类型枚举
 *
 */
public enum ItemType {
    FIGURE("数字",1),
    MULTIPLE("单选",2),
    MULTISELECT("多选",3),
    CHARACTER("文字",4),
    DATE("日期",5),
    TIME("时间",6),
    ELSE("其他",7);

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
