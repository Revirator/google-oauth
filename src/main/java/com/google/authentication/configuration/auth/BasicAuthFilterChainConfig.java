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
 * Protect all admin endpoints with basic authentication.
 */
@Configuration
public class BasicAuthFilterChainConfig {
    @Bean
    @Order(1)
    public SecurityFilterChain basicAuthFilterChain(HttpSecurity http, RequestCache requestCache) throws Exception {
        http.securityMatcher("/admin/**");
        http.httpBasic(Customizer.withDefaults());
        http.authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
        );
        http.requestCache(cache -> cache
                .requestCache(requestCache)
        );
        http.sessionManagement(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
