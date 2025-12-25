package com.example.demo.controller;

import com.example.demo.entity.ServiceCounter;
import com.example.demo.entity.Token;
import com.example.demo.service.ServiceCounterService;
import com.example.demo.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final ServiceCounterService counterService;
    private final TokenService tokenService;

    public AdminController(ServiceCounterService counterService, TokenService tokenService) {
        this.counterService = counterService;
        this.tokenService = tokenService;
    }

    @GetMapping("/counters")
    public ResponseEntity<List<ServiceCounter>> getAllCounters() {
        List<ServiceCounter> counters = counterService.getActiveCounters();
        return ResponseEntity.ok(counters);
    }

    @GetMapping("/tokens/waiting")
    public ResponseEntity<List<Token>> getAllWaitingTokens() {
        // This would need a service method to get all waiting tokens across all counters
        return ResponseEntity.ok(List.of());
    }

    @PutMapping("/counters/{id}/toggle")
    public ResponseEntity<String> toggleCounter(@PathVariable Long id) {
        // This would need implementation to toggle counter active status
        return ResponseEntity.ok("Counter status toggled");
    }
}