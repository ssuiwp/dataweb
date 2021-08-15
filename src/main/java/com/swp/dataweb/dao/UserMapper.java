package com.swp.dataweb.dao;


import com.swp.dataweb.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

/**
 * 用户信息操作
 */
@Mapper
public interface UserMapper {

    /**
     * 注册用户
     * @param user 用户
     * @return int
     */
    @Insert("insert into user(name, password) " +
            "values ( " +
            " #{name}, #{password} " +
            ")")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int addUser(User user);

}
