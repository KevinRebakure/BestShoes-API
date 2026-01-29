package com.rebakure.bestshoes.config.security;

import com.rebakure.bestshoes.common.Roles;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

@Component
public class CollectionSecurityRules implements  SecurityRules {
    @Override
    public void configure(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        registry.requestMatchers( "/collections/**").hasRole(Roles.ADMIN.toString());
    }
}
