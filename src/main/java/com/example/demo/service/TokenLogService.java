package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.TokenLog;

public interface TokenLogService {

    TokenLog addLog(Long tokenId, String message);

    List<TokenLog> getLogs(Long tokenId);
}
