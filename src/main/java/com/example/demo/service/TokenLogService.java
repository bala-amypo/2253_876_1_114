package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.TokenLog;

public interface TokenLogService {

    // Get all logs of a token
    List<TokenLog> getLogs(Long tokenId);

    // Save log (already used internally)
    TokenLog saveLog(Long tokenId, String message);

    // Required because controller calls addLog()
    TokenLog addLog(Long tokenId, String message);
}
