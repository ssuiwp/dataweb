package com.swp.dataweb.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.swp.dataweb.entity.UserSubjectType;
import com.swp.dataweb.entity.response.PageResult;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SubjectTypeMapper extends BaseMapper<UserSubjectType> {
    int updateOrInsert(UserSubjectType type);

    @Select("select count(*) from user_subject_type where user_id = #{userId}")
    int selectAllCount(@Param("userId") Long userId);

    List<UserSubjectType> findType(@Param("page") PageResult type,
                                   @Param("start") int start,
                                   @Param("userId")Long userId);
}
