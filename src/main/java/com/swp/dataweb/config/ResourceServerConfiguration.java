package com.swp.dataweb.config;

import com.swp.dataweb.handler.ParamFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    /**
     * @description：资源认证服务器，配置对/encrypt/publickey接口的放行
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests().antMatchers("/user/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
//        http.addFilterAt(new ParamFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}

