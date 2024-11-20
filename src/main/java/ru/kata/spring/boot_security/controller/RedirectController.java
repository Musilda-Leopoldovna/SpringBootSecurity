package ru.kata.spring.boot_security.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {

    @GetMapping("/")
    public String redirectToPage(HttpServletRequest request) {
        if (request.getUserPrincipal() != null) {
            return "/user";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        String query = request.getQueryString();
        if (query != null && query.contains("logout")) {
            return "redirect:/login";
        }
        return "login";
    }
}
