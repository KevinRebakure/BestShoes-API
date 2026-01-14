package com.rebakure.bestshoes.config;

import com.rebakure.bestshoes.common.Roles;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

@Component
public class ProductSecurityRules implements SecurityRules {
    @Override
    public void configure(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        registry.requestMatchers(HttpMethod.POST, "/products/**").hasRole(Roles.ADMIN.toString());
        registry.requestMatchers(HttpMethod.PUT, "/products/**").hasRole(Roles.ADMIN.toString());
        registry.requestMatchers(HttpMethod.DELETE, "/products/**").hasRole(Roles.ADMIN.toString());
        registry.requestMatchers(HttpMethod.GET, "/products/**").permitAll();

    }
}
