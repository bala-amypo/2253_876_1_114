package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
    AuthResponse response = userService.register(request);
    return ResponseEntity.ok(response);
}

@PostMapping("/login")
public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
    AuthResponse response = userService.login(request);
    return ResponseEntity.ok(response);
}


}
