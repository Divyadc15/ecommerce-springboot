 package com.pro1.ecommerce.controller;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.pro1.ecommerce.dto.LoginRequest;
import com.pro1.ecommerce.dto.LoginResponse;
import com.pro1.ecommerce.dto.RegisterRequest;
import com.pro1.ecommerce.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return userService.login(request);
    }
}