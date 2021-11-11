package com.swp.dataweb.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swp.dataweb.entity.response.SysResult;
import com.swp.dataweb.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean("passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                //去除session管理  选择使用jwt令牌
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 自定义权限拒绝处理类
//                .and()
//                .exceptionHandling()
//                .accessDeniedHandler(restfulAccessDeniedHandler())
//                .authenticationEntryPoint(restAuthenticationEntryPoint())
        // 自定义权限拦截器JWT过滤器
//                .and()
//                .addFilterBefore(/*jwtAuthenticationTokenFilter(),*/ UsernamePasswordAuthenticationFilter.class);
        http.authorizeRequests()
                .antMatchers("/user/register"/*,"/user/test"*/).permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated();
//                //.antMatchers("/test","/tests").authenticated()
//                .antMatchers("/login").permitAll();
        http.formLogin()
                .loginPage("/user/login")
                .loginProcessingUrl("/user/login").permitAll()
                .successHandler(successHandler())
                .failureHandler(failureHandler());
        http.logout().logoutUrl("/user/logout")
                // 无效会话
                .invalidateHttpSession(true)
                // 清除身份验证
                .clearAuthentication(true)
                .permitAll();
    }
//    @Bean
//    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
//        return new JwtAuthenticationTokenFilter();
//    }
//
//    @Bean
//    public RestfulAccessDeniedHandler restfulAccessDeniedHandler() {
//        return new RestfulAccessDeniedHandler();
//    }
//
//    @Bean
//    public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
//        return new RestAuthenticationEntryPoint();
//    }

    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return (request, response, authentication) -> {

            writeJsonToClient(response, SysResult.error());
        };
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {

            writeJsonToClient(response, SysResult.success());
        };
    }

    private void writeJsonToClient(HttpServletResponse response, Object o) throws IOException {
        PrintWriter writer = response.getWriter();
        String jsonResult = new ObjectMapper().writeValueAsString(o);

        writer.write(jsonResult);
        writer.flush();
    }

    @Override
    @Bean("authenticationManager")
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password("$2a$10$RMuFXGQ5AtH4wOvkUqyvuecpqUSeoxZYqilXzbz50dceRsga.WYiq") //123
//                .roles("admin")
//                .and()
//                .withUser("sang")
//                .password("$2a$10$RMuFXGQ5AtH4wOvkUqyvuecpqUSeoxZYqilXzbz50dceRsga.WYiq") //123
//                .roles("user");
        auth.userDetailsService(userDetailsService());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        System.out.println("configure(WebSecurity web)...");
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/static/**");
        web.ignoring().antMatchers("/bootstrap/**");
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/images/**");
        web.ignoring().antMatchers("/**/favicon.ico");
        web.ignoring().antMatchers("/lib/**");
        web.ignoring().antMatchers("/fonts/**");
        web.ignoring().antMatchers("/lang/**");
        web.ignoring().antMatchers("/login.html");
        //解决服务注册url被拦截的问题
        web.ignoring().antMatchers("/**/*.json");
    }


}
