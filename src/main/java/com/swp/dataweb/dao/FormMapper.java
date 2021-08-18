package com.swp.dataweb.dao;

import com.swp.dataweb.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * 表单操作
 */
@Mapper
public interface FormMapper {

    /**
     * 新建表单
     * @param form 表单
     * @return int
     */
    @Insert("insert into form(name, subject_id , subject_name, postscript) " +
            "values ( " +
            " #{form.name}, #{form.subject.id}, #{form.subject.name} , #{form.postscript}" +
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addForm(@Param("form") Form form);


    /**
     *添加form与item的关系
     */
    @Insert("<script>" +
            " insert into item_form_relation(form_id, item_id, creator) " +
            "  values  " +
            "       <foreach collection='form.multiItems' item='items' separator=','> " +
            "            ( #{form.id}, #{items.id}, #{form.creator} ) " +
            "       </foreach> " +
            " </script>")
    int addRelation(@Param("form") Form form);


    /**
     * 分页查询表单
     */
    @Select("<script> " +
            "   SELECT " +
            "       id, name, subject_name, creator, create_time, postscript " +
            "   FROM " +
            "       form " +
            "   WHERE " +
            "       1 = 1" +
            "       <if test='query.subjectNames != null'> " +
            "           <foreach collection='query.subjectNames' item='sname' separator=',' open='(' close=')'> " +
            "           AND id like " +
            "               %#{sname}% " +
            "           </foreach>" +
            "       </if> " +
            "       <if test='query.formNames != null'>" +
            "           <foreach collection='query.formNames' item='fname' separator=',' open='(' close=')'> " +
            "           AND subject_name like " +
            "               %#{fname}% " +
            "           </foreach> " +
            "       </if> " +
            "   limit #{query.PageInfo.pageSize*(query.PageInfo.currentPage-1)}, #{pageSize} ;" +
            "</script>")
    @Results(id = "formResultMap" ,value = {
            @Result(column = "subject_name", property = "subjectName"),
            @Result(column = "create_time", property = "createTime"),
    })
    List<Form> getForm(@Param("query") FormQuery query);

    /**
     * 查询表单总数
     */
    @Select("select count(id) form form" )
    int getTotal();

    /**
     * 更新表单
     */
    @Update("<script> " +
            "   UPDATE " +
            "       form " +
            "   SET " +
            "       <if test='form.subject != null'> subject_name = #{form.subject.name} </if>" +
            "       <if test='form.postscript != null'>, postscript = #{form.postscript} </if>" +
            "   WHERE " +
            "       id = #{form.id} " +
            "</script> ")
    int updateForm(@Param("form") Form form);
    /**
     * 更新关联表
     */
    @Update("<script> " +
//            "       <if test='form.multiItems != null' >" +
            "   replace into " +
            "       item_form_relation(form_id,item_id) " +
            "   values " +
            "           <foreach collection='multiItems' item='items' separator=','> " +
            "               ( #{id}, #{items.id} ) " +
            "           </foreach> " +
//            "       </if>" +
            "</script> ")
    int updateRelation(Form form);

    /**
     * 删除表单
     */
    @Delete("delete from form where id = #{formId}")
    int deleteForm(@Param("formId") Long formId);

    /**
     * 删除表单的同时删除表单问项关联表内
     * 同时也要删除表单对应的数据
     */
    @Delete("delete from item_form_relation where form_id = #{formId}")
    int deleteRelation(@Param("formId") Long formId);

    /**
     * 通过subjectId查询表单
     */
    @Select("SELECT  id  FROM  form  WHERE subject_id = #{subjectId} ")
    List<Long> getFormId(@Param("subjectId") long subjectId);

}
