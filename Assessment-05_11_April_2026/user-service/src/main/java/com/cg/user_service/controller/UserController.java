package com.cg.user_service.controller;

import org.springframework.web.bind.annotation.*;
import com.cg.user_service.service.UserService;
import com.cg.user_service.entity.User;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return service.getUser(id);
    }
}