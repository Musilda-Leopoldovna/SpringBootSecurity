package ru.kata.spring.boot_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.entity.User;
import ru.kata.spring.boot_security.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

import java.security.Principal;

@Controller
public class UserController {

    private final UserService service;
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String redirectToPage(HttpServletRequest request) {
        if (request.getUserPrincipal() != null) {
            return "redirect:/user";
        }
        return "redirect:/login";
    }

    @GetMapping("/user")
    public String getUserData(Model model, Principal principal) {
        User user = service.getUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }
}
