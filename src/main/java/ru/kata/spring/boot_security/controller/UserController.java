package ru.kata.spring.boot_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.service.UserService;

@Controller
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String user(Model model) {
        model.addAttribute("user", userService.getListOfUsers());
        return "user";
    }
}
