package com.freshvotes.web;

import com.freshvotes.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @GetMapping("login")
    public String login() {
        return "login2";
    }

    @GetMapping("register")
    public String register(ModelMap model) {
        model.put("user", new User());
        return "register";
    }

    @PostMapping("register")
    public String registerPost(User user) {
        System.out.println(user);
        return "redirect:/register";
    }
}
