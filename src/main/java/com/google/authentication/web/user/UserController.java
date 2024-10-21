package com.google.authentication.web.user;

import com.google.authentication.model.User;
import com.google.authentication.service.UserService;
import com.google.authentication.util.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private static final String USER_ATTRIBUTE = "user";
    private static final String USER_TEMPLATE = "user";

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @GetMapping
    public String getUser(Authentication authentication, Model model) {
        var user = userService.getOrCreateUser(authentication);
        model.addAttribute(USER_ATTRIBUTE, user);
        return USER_TEMPLATE;
    }

    @PostMapping
    public String updateUser(Authentication authentication, @ModelAttribute User user, Model model) {
        var id = authenticationService.getUserId(authentication);
        Assert.state(id.equals(user.getId()), "Logged in user cannot update other users.");
        user = userService.updateUserInformation(id, user.getInformation());
        model.addAttribute(model.addAttribute(USER_ATTRIBUTE, user));
        return USER_TEMPLATE;
    }
}
