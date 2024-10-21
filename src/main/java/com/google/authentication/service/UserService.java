package com.google.authentication.service;

import com.google.authentication.model.User;
import com.google.authentication.persistence.UserRepository;
import com.google.authentication.util.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@AllArgsConstructor
public class UserService {
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    public User getUser(Authentication authentication) {
        var id = authenticationService.getUserId(authentication);
        return userRepository.findById(id).orElse(null);
    }

    public User getOrCreateUser(Authentication authentication) {
        var user = getUser(authentication);
        if (user == null) {
            user = authenticationService.createUser(authentication);
            userRepository.save(user);
        }
        return user;
    }

    public User updateUserInformation(String id, String information) {
        User user = userRepository.findById(id).orElse(null);
        Assert.notNull(user, "Logged in user must already exist in the database.");
        user.setInformation(information);
        return userRepository.save(user);
    }
}
