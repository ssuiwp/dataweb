package com.swp.dataweb.dao;

import com.swp.dataweb.entity.Form;
import com.swp.dataweb.entity.MultiItem;
import com.swp.dataweb.entity.MultiItemQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 问项操作
 */
@Mapper
public interface MultiItemMapper {

    /**
     * 新建问项
     */
    @Insert("insert into multiItem ( name, type, title, options ) " +
            "values ( " +
            " #{name}, #{type}, #{title}, #{options} " +
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addMultiItem(MultiItem multiItem);

    /**
     * 查询问项
     */
    @Select("<script> " +
            "   SELECT " +
            "       id, name, type, title, options, creator, create_time " +
            "   FROM " +
            "       multiitem " +
            "   WHERE " +
            "       1 = 1" +
            "       <if test='query.itemNames != null'> " +
            "           AND name IN " +
            "           <foreach collection='query.itemNames' item='name' separator=',' open='(' close=')'> " +
            "               #{name} " +
            "           </foreach>" +
            "       </if> " +
            "       <if test='query.types != null'>" +
            "           AND type IN " +
            "           <foreach collection='query.types' item='type' separator=',' open='(' close=')'> " +
            "               #{type} " +
            "           </foreach> " +
            "       </if> " +
            "</script>")
    @Results(id = "itemResultMap" , value = {
            @Result(column = "create_time", property = "createTime")
    })
    List<MultiItem> getMultiItem(@Param("query") MultiItemQuery query);

    @Select("   SELECT " +
            "       id, name, type, title, options, creator, create_time " +
            "   FROM " +
            "       multiitem " +
            "   where " +
            "   id =#{id}" )
    @ResultMap("itemResultMap")
    MultiItem getMultiItemById(@Param("id") long multiItemId);



}
