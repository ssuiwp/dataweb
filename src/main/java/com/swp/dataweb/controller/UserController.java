package com.swp.dataweb.controller;

import com.swp.dataweb.entity.User;
import com.swp.dataweb.entity.response.SysResult;
import com.swp.dataweb.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
@CrossOrigin
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/test")
    public SysResult test(){
        log.info("测试权限");
        return SysResult.success();
    }

    @PostMapping("/register")
    public SysResult addUser(@RequestBody User user){
        boolean result = userService.addUser(user);
        log.info(String.valueOf(user));
        if(result) {
            return SysResult.success();
        }
        return SysResult.error();
    }

    @PostMapping("/update")
    public SysResult updateUser(@RequestBody User user){
        boolean result = userService.updateUser(user);
        log.info(String.valueOf(user));
        if(result) {
            return SysResult.success();
        }
        return SysResult.error();
    }

}
