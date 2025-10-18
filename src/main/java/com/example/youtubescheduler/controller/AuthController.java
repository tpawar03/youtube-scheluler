package com.example.youtubescheduler.controller;

import com.example.youtubescheduler.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        HttpSession session, Model model) {
        if (authService.authenticate(username, password)) {

            return "redirect:/tvmode";
        }
        model.addAttribute("error", "Invalid credentials");
        return "login";
    }

}
