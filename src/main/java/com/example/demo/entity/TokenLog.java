package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.TokenLog;

public interface TokenLogService {

    // Get all logs for a token
    List<TokenLog> getLogs(Long tokenId);

    // Create and save a log message
    TokenLog saveLog(Long tokenId, String message);
}
