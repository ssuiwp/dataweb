package com.swp.dataweb.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.swp.dataweb.entity.Subject;
import com.swp.dataweb.entity.TData;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 数据操作
 */
@Mapper
public interface TDataMapper extends BaseMapper<TData> {

    /**
     * 添加数据
     */
//    @Insert("<script> " +
//            "   insert into data " +
//            "       (item_id, form_id, data, creator) " +
//            "   values (" +
//            "           <foreach collection='list' item='d' separator=','> " +
//            "               ( #{d.itemId}, #{d.formId}, #{d.data} ,#{creator})" +
//            "           </foreach>" +
//            "       )" +
//            "</script>")
//    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into data" +
            "(form_id,data,creator)" +
            "values(" +
            "   #{data.formId},#{data.data},#{creator}" +
            ")")
    int addTData(@Param("data") TData data,
                 @Param("creator") String creator);



    /**
     * 查询数据需要查询课表，查询课表相关的表单，查询表单相关的问项，通过表单和问项id锁定问项数据
     */
//    @Select(
//            "SELECT * FROM tData" +
//                " WHERE " +
//                    " form_id in " +
//                " (SELECT id FROM form WHERE subject_id = '#{id}')"
//    )
//    @Results(id = "TDataResultMap" , value = {
//            @Result(column = "form_id", property = "formId"),
//            @Result(column = "item_id", property = "itemId"),
//            @Result(column = "create_time", property = "createTime")
//    })
    @Select("select id,form_id,data,created,updated,creator from data where form_id = #{formId}")
    TData getTData(long formId);

//    /**
//     * 获取数据总数
//     */
//    @Select("select count(id) from tdata")
//    int getTotal();



    /**
     * 修改数据
     */
    @Update("<script> " +
            "   UPDATE " +
            "       tdata " +
            "   SET " +
            "       data = case " +
            "           <foreach collection='list' item='d' separator='\n'> " +
            "                when form_id = #{d.formId} and item_id = #{d.itemId} then #{d.data} " +
            "           </foreach>" +
            "   END" +
            "</script> ")
    int updateTData(@Param("list") List<TData> data);

    /**
     * 删除数据(通过课题名)
     */
    @Delete("delete from tdata where exits  (select id from form where subject_name = #{name})")
    int deleteTData1(@Param("name") String subjectName);

    /**
     * 删除数据(通过表单名)
     */
    @Delete("delete from data where form_id = #{id}")
    int deleteTData(@Param("id") long formId);

}
