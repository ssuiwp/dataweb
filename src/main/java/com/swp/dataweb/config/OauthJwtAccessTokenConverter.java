package com.swp.dataweb.config;

import com.swp.dataweb.service.UserService;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

public class OauthJwtAccessTokenConverter extends JwtAccessTokenConverter {
    public OauthJwtAccessTokenConverter(UserService userService) {
        super.setAccessTokenConverter(new OauthAccessTokenConverter(userService));
    }
}
