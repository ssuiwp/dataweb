package com.swp.dataweb.utils;

import com.swp.dataweb.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigInteger;

public class Utils {
    /** 获取用户id*/
    public static Long getUserId() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }
    /**获取用户名*/
    public static String getUserName() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }
    /**获取用户昵称*/
    public static String getNickName() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getNickname();
    }
}
