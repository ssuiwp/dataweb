package com.swp.dataweb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.swp.dataweb.dao.UserMapper;
import com.swp.dataweb.entity.User;
import com.swp.dataweb.utils.Utils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserService implements UserDetailsService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Transactional
    public boolean addUser(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        String password = user.getPassword();
        queryWrapper.eq("username",user.getUsername());
        User one = userMapper.selectOne(queryWrapper);
        if(one!=null)return false;
        String password1 = Utils.getPassword(password);
        user.setPassword(passwordEncoder.encode(password1));
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
