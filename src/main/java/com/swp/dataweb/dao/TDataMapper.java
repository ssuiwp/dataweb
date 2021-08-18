package com.swp.dataweb.dao;

import com.swp.dataweb.entity.Subject;
import com.swp.dataweb.entity.TData;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 数据操作
 */
@Mapper
public interface TDataMapper {

    /**
     * 添加数据
     */
    @Insert("<script> " +
            "   insert into tdata " +
            "       (item_id, form_id, data) " +
            "   values (" +
            "           <foreach collection='list' item='d' separator=','> " +
            "               ( #{d.itemId}, #{d.formId}, #{d.data} )" +
            "           </foreach>" +
            "       )" +
            "</script>")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addTData(@Param("list") List<TData> data);



    /**
     * 查询数据需要查询课表，查询课表相关的表单，查询表单相关的问项，通过表单和问项id锁定问项数据
     */
    @Select(
            "SELECT * FROM tData" +
                " WHERE " +
                    " form_id in " +
                " (SELECT id FROM form WHERE subject_id = '#{id}')"
    )
    @Results(id = "TDataResultMap" , value = {
            @Result(column = "form_id", property = "formId"),
            @Result(column = "item_id", property = "itemId"),
            @Result(column = "create_time", property = "createTime")
    })
    List<TData> getTData(Subject subject);

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
    @Delete("delete from tdata where form_id = #{id}")
    int deleteTData(@Param("id") long formId);

}
