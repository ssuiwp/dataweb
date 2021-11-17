package com.swp.dataweb.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swp.dataweb.entity.Subject;
import com.swp.dataweb.entity.SubjectQuery;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 课题操作
 */
@Mapper
public interface SubjectMapper extends BaseMapper<Subject> {

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
            "       id, subject_name, subject_type, unit, owner, created, updated, state, postscript " +
            "   FROM " +
            "       subject " +
            "   WHERE " +
            "       1 = 1 and user_id = #{userId}" +
            "       <if test='query.subjectName != null &amp;&amp; query.subjectName.length()>0'> " +
//            "           <foreach collection='query.subjectName' item='sname' separator=',' open='(' close=')'> " +
            "           AND subject_name like " +
            "               \"%\"#{query.subjectName}\"%\" " +
//            "           </foreach>" +
            "       </if> " +
            "       <if test='query.subjectType != null &amp;&amp; query.subjectType.length()>0'>" +
//            "           <foreach collection='query.subjectType' item='stype' separator=',' open='(' close=')'> " +
            "           AND subject_type like " +
            "               \"%\"#{query.subjectType}\"%\" " +
//            "           </foreach> " +
            "       </if> " +
            "       <if test='query.unit != null &amp;&amp; query.unit.length()>0'>" +
//            "           <foreach collection='query.unit' item='unit' separator=',' open='(' close=')'> " +
            "           AND unit like " +
            "               \"%\"#{query.unit}\"%\" " +
//            "           </foreach> " +
            "       </if> " +
            "   limit #{start}, #{query.size} ;" +
            "</script>")
    @Results(id = "subjectResultMap")
    List<Subject> getSubjects(@Param("query") SubjectQuery query,
                              @Param("start") Long start,
                              @Param("userId") Long userId);

    /**
     * 查询课题总数
     */
    @Select("select count(*) from subject where user_id = #{userId}")
    int getTotal(@Param("userId") Long userId);

    /**
     * 更新课题
     */
    @Update("<script> " +
            "   UPDATE " +
            "       subject " +
            "   SET " +
            "       <if test='subjectType != null'> subject_type = #{subjectType}, </if>" +
            "       <if test='owner != null'> owner = #{owner} </if>" +
            "       <if test='postscript != null'>, postscript = #{postscript} </if>" +
            "       <if test='state != null'>, state = #{state} </if>" +
            "   WHERE " +
            "       id = #{id} " +
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
