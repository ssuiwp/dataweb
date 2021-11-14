package com.swp.dataweb.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.swp.dataweb.entity.Item;
import com.swp.dataweb.entity.MultiItemQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 问项操作
 */
@Mapper
public interface ItemMapper extends BaseMapper<Item> {

    /**
     * 新建问项
     */
//    @Insert("insert into multiItem ( name, type, title, options ) " +
//            "values ( " +
//            " #{name}, #{type}, #{title}, #{options} " +
//            ")")
//    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addItem(@Param("userId") Long userId,
                @Param("itemId") Long itemId);

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
            "           <foreach collection='query.itemNames' item='name' separator=',' open='(' close=')'> " +
            "           AND name like " +
            "               %#{name}% " +
            "           </foreach>" +
            "       </if> " +
            "       <if test='query.types != null'>" +
            "           <foreach collection='query.types' item='type' separator=',' open='(' close=')'> " +
            "           AND type like " +
            "               %#{type}% " +
            "           </foreach> " +
            "       </if> " +
            "   limit #{query.PageInfo.pageSize*(query.PageInfo.currentPage-1)}, #{pageSize} ;" +
            "</script>")
    @Results(id = "itemResultMap" , value = {
            @Result(column = "create_time", property = "createTime")
    })
    List<Item> getMultiItem(@Param("query") MultiItemQuery query);

    /**
     * 查询问项总数
     */
    @Select("select count(id) from multiitem")
    int getTotal();


    @Select("   SELECT " +
            "       id, name, type, title, options, creator, create_time " +
            "   FROM " +
            "       multiitem " +
            "   where " +
            "   id =#{id}" )
    @ResultMap("itemResultMap")
    Item getMultiItemById(@Param("id") long multiItemId);


    List<Item> findItem(@Param("userId") Long userId,
                        @Param("itemName") String itemName);
}
