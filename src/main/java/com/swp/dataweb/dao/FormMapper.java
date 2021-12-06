package com.swp.dataweb.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.swp.dataweb.entity.*;
import com.swp.dataweb.entity.query.FormQuery;
import com.swp.dataweb.entity.request.ItemSort;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * 表单操作
 */
@Mapper
public interface FormMapper extends BaseMapper<Form> {


    /**
     * 添加form与item的关系
     */
    @Insert("<script>" +
            " insert into form_item (form_id, item_id, creator) " +
            "  values  " +
            "       <foreach collection='form.itemIds' item='itemId' separator=','> " +
            "            ( #{form.id}, #{itemId}, #{form.creator} ) " +
            "       </foreach> " +
            " </script>")
    int addRelation(@Param("form") Form form);

    /**
     * 分页查询表单
     */
    @Select("<script> " +
            "   SELECT " +
            "       id, form_name,subject_id , subject_name, creator, created, updated, postscript " +
            "   FROM " +
            "       form " +
            "   WHERE " +
            "       subject_id = #{subjectId}" +
            "       <if test='query!= null &amp;&amp; query.length()>0 '> " +
//            "           <foreach collection='query.subjectNames' item='sname' separator=',' open='(' close=')'> " +
            "           AND form_name like " +
            "               \"%\"#{query}\"%\" " +
//            "           </foreach>" +
            "       </if> " +
            "   limit #{start}, #{size}" +
            "</script>")
    @Results(id = "formResultMap")
    List<Form> getForms(@Param("query") String query,
                        @Param("start") long start,
                        @Param("subjectId") Long subjectId,
                        @Param("size") int size);

    /**
     * 查询表单总数
     */
    @Select("select count(*) from form where subject_id = #{subjectId}")
    int getTotal(@Param("subjectId") Long subjectId);

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
            "   update form_item" +
            "       <set> " +
            "           order_num = " +
            "           <foreach collection='itemSort.itemSorts' item='item' open=\"case item_id\" close=\"else order_num end\" separator=' '> " +
            "              when #{item.id} then #{item.orderNum}  " +
            "           </foreach> " +
            "    where form_id = #{itemSort.formId};" +
            "       </set>" +
            "</script> ")
    int updateRelation(@Param("itemSort") ItemSort itemSort);

    /**
     * 删除表单
     */
    @Delete("delete from form where id = #{formId}")
    int deleteForm(@Param("formId") Long formId);

    /**
     * 删除表单的同时删除表单问项关联表内
     * 同时也要删除表单对应的数据
     */
    @Delete("delete from form_item where form_id = #{formId}")
    int deleteRelation(@Param("formId") Long formId);

    /**
     * 通过subjectId查询表单
     */
    @Select("SELECT  id  FROM  form  WHERE subject_id = #{subjectId} ")
    List<Long> getFormId(@Param("subjectId") long subjectId);

}
