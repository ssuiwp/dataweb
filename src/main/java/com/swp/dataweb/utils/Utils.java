package com.swp.dataweb.utils;

import com.alibaba.druid.util.StringUtils;
import com.swp.dataweb.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigInteger;

public class Utils {
    public static BigInteger getUserId(){
        return ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }

}
