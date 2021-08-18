package com.swp.dataweb.dao;


import com.swp.dataweb.entity.Subject;
import com.swp.dataweb.entity.SubjectQuery;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 课题操作
 */
@Mapper
public interface SubjectMapper {

    //property是对方的，column是自己java内部的

    /**
     * 新建课题
     * @param subject 课题
     * @return int
     */
    @Insert("insert into subject(name, type, unit, owner, postscript) " +
            "values ( " +
            " #{name}, #{type}, #{unit}, #{owner}, #{postscript} " +
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addSubject(Subject subject);


    /**
     * 查询课题
     */
    @Select("<script> " +
            "   SELECT " +
            "       id, name, type, unit, owner, create_time, state, postscript " +
            "   FROM " +
            "       subject " +
            "   WHERE " +
            "       1 = 1" +
            "       <if test='query.subjectNames != null'> " +
            "           <foreach collection='query.subjectNames' item='sname' separator=',' open='(' close=')'> " +
            "           AND name like " +
            "               %#{sname}% " +
            "           </foreach>" +
            "       </if> " +
            "       <if test='query.subjectType != null'>" +
            "           <foreach collection='query.subjectType' item='stype' separator=',' open='(' close=')'> " +
            "           AND type like " +
            "               %#{stype}% " +
            "           </foreach> " +
            "       </if> " +
            "       <if test='query.subjectUnit != null'>" +
            "           <foreach collection='query.subjectUnit' item='unit' separator=',' open='(' close=')'> " +
            "           AND unit like " +
            "               %#{unit}% " +
            "           </foreach> " +
            "       </if> " +
            "   limit #{query.PageInfo.pageSize*(query.PageInfo.currentPage-1)}, #{pageSize} ;" +
            "</script>")
    @Results(id = "subjectResultMap", value = {
            @Result(column = "create_time" , property = "createTime")
    })
    List<Subject> getSubjects(@Param("query") SubjectQuery query);

    /**
     * 查询课题总数
     */
    @Select("select count(id) from subject")
    int getTotal();

    /**
     * 更新课题
     */
    @Update("<script> " +
            "   UPDATE " +
            "       subject " +
            "   SET " +
            "       <if test='type != null'> type = #{type}, </if>" +
            "       <if test='owner != null'> owner = #{owner} </if>" +
            "       <if test='postscript != null'>, postscript = #{postscript} </if>" +
            "   WHERE " +
            "       name = #{name} " +
            "</script> ")
    int updateSubject(Subject subject);

    /**
     * 删除课题
     */
    @Delete("delete from subject where id = #{id}")
    int deleteSubject(@Param("id") Long subjectId);

    /**
     * 通过课题查询form
     */



}
