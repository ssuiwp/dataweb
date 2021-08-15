package com.swp.dataweb.entity;

/**
 * 问项类型枚举
 *
 */
public enum ItemTypeEnum {
    Figure("数字"),
    Multiple("单选"),
    MultiSelect("多选"),
    Character("文字"),
    Date("日期"),
    Time("时间"),
    Else("其他");

    public final String itemType;
    ItemTypeEnum(String itemType){
        this.itemType = itemType;
    }
    public String getItemType(){
        return itemType;
    }

}
