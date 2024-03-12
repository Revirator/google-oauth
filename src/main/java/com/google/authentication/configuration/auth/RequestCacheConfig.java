package com.google.authentication.configuration.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

@Configuration
public class RequestCacheConfig {
    @Bean
    public RequestCache requestCache() {
        var requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName(null);
        return requestCache;
    }
}
