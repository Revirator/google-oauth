package com.google.authentication.util;

import com.google.authentication.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * The authentication's principle should follow Google's structure.
 */
@Service
public class AuthenticationService {
    public String getUserId(Authentication authentication) {
        var principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();
        var id = principal.<String>getAttribute("sub");
        Assert.notNull(id, "User logged in with Google must have an id.");
        return id;
    }

    public User createUser(Authentication authentication) {
        var id = getUserId(authentication);
        var principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();
        return User.builder()
                .id(id)
                .givenName(principal.getAttribute("given_name"))
                .familyName(principal.getAttribute("family_name"))
                .email(principal.getAttribute("email"))
                .build();
    }
}
