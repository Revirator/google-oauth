package com.google.authentication.web.admin;

import com.google.authentication.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private UserRepository userRepository;

    @GetMapping
    public String admin(Model model) {
        var users = userRepository.findAll();
        model.addAttribute("users", users);
        return "admin";
    }
}
