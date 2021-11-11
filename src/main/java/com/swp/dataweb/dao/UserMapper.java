package com.swp.dataweb.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.swp.dataweb.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

/**
 * 用户信息操作
 */
public interface UserMapper extends BaseMapper<User> {

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
