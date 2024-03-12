package com.google.authentication.service;

import com.google.authentication.model.User;
import com.google.authentication.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser(Authentication authentication) {
        var principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();
        var id = principal.<String>getAttribute("sub");
        if (id == null) {
            throw new IllegalArgumentException("Unknown user");
        }
        var user = userRepository.findById(id).orElse(createNewUser(id, principal));
        userRepository.save(user);
        return user;
    }

    public void updateUserInformation(String id, String information) {
        userRepository.findById(id).ifPresent(it -> it.setInformation(information));
    }

    private User createNewUser(String id, OAuth2AuthenticatedPrincipal principal) {
        return User.builder()
                .id(id)
                .givenName(principal.getAttribute("given_name"))
                .familyName(principal.getAttribute("family_name"))
                .email(principal.getAttribute("email"))
                .build();
    }
}
