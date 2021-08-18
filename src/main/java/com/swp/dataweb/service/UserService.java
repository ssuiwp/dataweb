package com.swp.dataweb.service;

import com.swp.dataweb.dao.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

}
