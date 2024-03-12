package com.google.authentication.web.user;

import com.google.authentication.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public String user(Authentication authentication, Model model) {
        var user = userService.getUser(authentication);
        model.addAttribute("user", user);
        return "user";
    }

    @PostMapping("/{id}/information")
    public void information(Authentication authentication, @PathVariable String id, @RequestBody String information) {
        userService.updateUserInformation(id, information);
    }
}
