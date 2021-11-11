package com.swp.dataweb.config;

import com.swp.dataweb.service.UserService;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.stereotype.Component;

public class OauthAccessTokenConverter extends DefaultAccessTokenConverter {
    public OauthAccessTokenConverter(UserService userService) {
        DefaultUserAuthenticationConverter converter = new DefaultUserAuthenticationConverter();
        converter.setUserDetailsService(userService);
        super.setUserTokenConverter(converter);
    }
}
