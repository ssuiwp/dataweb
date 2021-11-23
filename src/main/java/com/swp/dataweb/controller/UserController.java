package com.swp.dataweb.controller;

import com.swp.dataweb.entity.User;
import com.swp.dataweb.entity.UserDesc;
import com.swp.dataweb.entity.response.SysResult;
import com.swp.dataweb.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
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
    public SysResult test() {
        log.info("测试权限");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("principal: " + principal);

        return SysResult.success();
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public SysResult addUser(@Validated @RequestBody User user) {
        boolean result = userService.addUser(user);
//        log.info(String.valueOf(user));
        if (result) {
            return SysResult.success();
        }
        return SysResult.error();
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @PostMapping("/update")
    public SysResult updateUser(@Validated @RequestBody User user) {
        boolean result = userService.updateUser(user);
        log.info(String.valueOf(user));
        if (result) {
            return SysResult.success();
        }
        return SysResult.error();
    }

    /**
     * 获取用户信息
     * @return
     */
    @PostMapping("/userDesc")
    public SysResult findUserDesc() {
        User result = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDesc user = new UserDesc()
                .setUsername(result.getUsername())
                .setEmail(result.getEmail())
                .setId(result.getId())
                .setIphone(result.getIphone())
                .setNickname(result.getNickname());
        return SysResult.success(user);
    }

}
