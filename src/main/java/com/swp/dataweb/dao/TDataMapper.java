package com.swp.dataweb.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.swp.dataweb.entity.Subject;
import com.swp.dataweb.entity.TData;

import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * 数据操作
 */
@Mapper
public interface TDataMapper extends BaseMapper<TData> {

    /**
     * 添加数据
     */

    @Insert("insert into data" +
            "(form_id,data,creator)" +
            "values(" +
            "   #{data.formId},#{data.data},#{creator}" +
            ")")
    int addTData(@Param("data") TData data,
                 @Param("creator") String creator);





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

    /**
     * 通过课题id获取所有表单id在通过表单id获取所有的课题数据
     * @param subjectId
     */
    List<TData> getTDataBySubjectId(@Param("subjectId") Long subjectId,
                                    @Param("startDate") Date startDate,
                                    @Param("endDate") Date endDate);


    /**
     * 通过表单id获取数据
     */
    List<TData> getTDataByFormIds(@Param("formIds") List<Long> formIds,
                                  @Param("startDate") Date startDate,
                                  @Param("endDate") Date endDate);
}
