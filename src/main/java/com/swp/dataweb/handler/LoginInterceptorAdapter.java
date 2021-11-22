package com.swp.dataweb.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Component
public class LoginInterceptorAdapter extends HandlerInterceptorAdapter {
//    @Override
//    public ModelAndView postHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String path = request.getServletPath();
//        //验证session
//        Object username = request.getSession().getAttribute("username");
//        if(username !=null){
//
//        }
//        //如果为空就去登录
//        else {
//            response.sendRedirect("/user/login");
//
//        }
//    }
}
