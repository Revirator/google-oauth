package com.google.authentication.configuration.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.RequestCache;

/**
 * Protect all business endpoints with Google OAuth2.
 */
@Configuration
public class BusinessFilterChainConfig {
    @Bean
    @Order(2)
    public SecurityFilterChain businessFilterChain(HttpSecurity http, RequestCache requestCache) throws Exception {
        http.securityMatcher("/user/**", "/oauth2/**", "/login/**");
        http.oauth2Login(Customizer.withDefaults());
        http.authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
        );
        http.requestCache(cache -> cache
                .requestCache(requestCache)
        );
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
