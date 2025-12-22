package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.TokenLog;
import com.example.demo.service.TokenLogService;

@RestController
@RequestMapping("/tokens")
public class TokenLogController {

    private final TokenLogService tokenLogService;

    public TokenLogController(TokenLogService tokenLogService) {
        this.tokenLogService = tokenLogService;
    }

    @GetMapping("/{tokenId}/logs")
    public ResponseEntity<List<TokenLog>> getLogs(@PathVariable Long tokenId) {
        return ResponseEntity.ok(tokenLogService.getLogs(tokenId));
    }

    @PostMapping("/{tokenId}/logs")
    public ResponseEntity<TokenLog> addLog(
            @PathVariable Long tokenId,
            @RequestBody String message) {

        return ResponseEntity.ok(tokenLogService.addLog(tokenId, message));
    }
}
