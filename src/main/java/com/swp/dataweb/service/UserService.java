package com.swp.dataweb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.swp.dataweb.dao.UserMapper;
import com.swp.dataweb.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserService implements UserDetailsService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Transactional
    public boolean addUser(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername());
        User one = userMapper.selectOne(queryWrapper);
        if(one!=null)return false;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.insert(user)>0;
    }

    @Transactional
    public boolean updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.updateById(user)>0;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        QueryWrapper<User> query = new QueryWrapper<>();
//        boolean flag = s==null||s.isEmpty();
        query.eq("username",s);
        User user1 = userMapper.selectOne(query);
        if(user1==null){
            throw new UsernameNotFoundException("user not exist");
        }
//        return new org.springframework.security.core.userdetails.User(
//                s,user1.getPassword(), AuthorityUtils.createAuthorityList("auth"));
        return user1;
    }

}
