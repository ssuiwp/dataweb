package com.swp.dataweb.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Service
public class BaseService {
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (StringUtils.isNotEmpty(name) && name.equalsIgnoreCase(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }
}
