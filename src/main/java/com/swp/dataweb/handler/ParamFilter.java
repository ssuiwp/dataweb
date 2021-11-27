package com.swp.dataweb.handler;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Deprecated
/**
 * 请求设置
 */
//@Component
@Slf4j
public class ParamFilter extends UsernamePasswordAuthenticationFilter {


//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        String param = request.getParameter("password");
//        if (StringUtils.isBlank(param)) {
//            chain.doFilter(request, response);
//        }
//        else {
////            byte[] bytes = new BASE64Decoder().decodeBuffer(param);
////            String decode = new String(bytes);
////            Map<String, Object> map = new HashMap<>();
////            map.put("key", new String[]{decode});
//            RequestParameterWrapper wrapper = new RequestParameterWrapper((HttpServletRequest) request);
//            System.out.println(wrapper.getParameter("password"));
//            chain.doFilter(wrapper, response);
//        }
//    }

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
//        String param = request.getParameter("password");
//        if (StringUtils.isBlank(param)) {
//            chain.doFilter(request, response);
//        }
//        else {
////            byte[] bytes = new BASE64Decoder().decodeBuffer(param);
////            String decode = new String(bytes);
////            Map<String, Object> map = new HashMap<>();
////            map.put("key", new String[]{decode});
//            RequestParameterWrapper wrapper = new RequestParameterWrapper((HttpServletRequest) request);
//            System.out.println(wrapper.getParameter("password"));
//            chain.doFilter(wrapper, response);
//        }
//    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        String param = request.getParameter("password");
//        if (StringUtils.isBlank(param)) {
//            chain.doFilter(request, response);
//        }
//        else {
//            byte[] bytes = new BASE64Decoder().decodeBuffer(param);
//            String decode = new String(bytes);
//            Map<String, Object> map = new HashMap<>();
//            map.put("key", new String[]{decode});
            RequestParameterWrapper wrapper = new RequestParameterWrapper((HttpServletRequest) request);
            System.out.println(wrapper.getParameter("password"));

//        }
        return super.attemptAuthentication(wrapper, response);
    }

//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
}


