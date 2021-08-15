package com.swp.dataweb.controller;

import com.swp.dataweb.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/dataweb/user")
public class UserController {

    @Resource
    private UserService userService;

}
