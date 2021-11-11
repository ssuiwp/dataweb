package com.swp.dataweb.config;

import com.swp.dataweb.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.jwk.JwkTokenStore;

import javax.annotation.Resource;

@Configuration
public class TokenConfig {
//    @Resource
//    JwtAccessTokenConverter jwtAccessTokenConverter;

    @Bean
    public TokenStore tokenStore(UserService userService){
        return new JwtTokenStore(jwtAccessTokenConverter(userService));
    }
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(UserService userService){
        JwtAccessTokenConverter converter=
                new OauthJwtAccessTokenConverter(userService);
        converter.setSigningKey(SIGNING_KEY);
//        converter.setAccessTokenConverter(new JwtAccessTokenConverter());
        return converter;
    }
    private static final String SIGNING_KEY = "AUTH";
}
