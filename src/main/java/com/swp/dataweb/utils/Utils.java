package com.swp.dataweb.utils;

import com.swp.dataweb.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigInteger;

public class Utils {
    public static Long getUserId() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }
    public static String getUserName() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }
}
