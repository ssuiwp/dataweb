package com.swp.dataweb.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.swp.dataweb.entity.Item;
import com.swp.dataweb.entity.query.ItemQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 问项操作
 */
@Mapper
public interface ItemMapper extends BaseMapper<Item> {
    /**
     * 查询问项
     */
    @Select("<script> " +
            "   SELECT " +
            "       id, item_name, type, title, options, creator, created, updated " +
            "   FROM " +
            "       item " +
            "   WHERE " +
            "       user_id = #{userId}" +
            "       <if test='query.itemName != null &amp;&amp; query.itemName.length()>0 '> " +
//            "           <foreach collection='query.itemNames' item='name' separator=',' open='(' close=')'> " +
            "           AND item_name like " +
            "               \"%\"#{query.itemName}\"%\" " +
//            "           </foreach>" +
            "       </if> " +
            "       <if test='query.type != null &amp;&amp; query.type.length()>0'>" +
//            "           <foreach collection='query.types' item='type' separator=',' open='(' close=')'> " +
            "           AND type like " +
            "               \"%\"#{query.type}\"%\" " +
//            "           </foreach> " +
            "       </if> " +
            "   limit #{start}, #{query.size} ;" +
            "</script>")
    @Results(id = "itemResultMap")
    List<Item> getItems(@Param("query") ItemQuery query,
                            @Param("start") long start,
                            @Param("userId") Long userId);

    /**
     * 查询问项总数
     */
    @Select("select count(*) from item where user_id = #{userId}")
    int getTotal(@Param("userId")Long userId);


    List<Item> getItem(long formId);
}
