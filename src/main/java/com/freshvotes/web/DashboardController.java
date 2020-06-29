package com.freshvotes.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DashboardController {

    @GetMapping("/")
    public String rootView() {
        return "index";
    }
    @GetMapping("/dashboard")
    public String dashboard(){
        return "dashboard";
    }

}
