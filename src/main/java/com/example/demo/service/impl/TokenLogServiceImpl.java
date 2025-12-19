package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Token;
import com.example.demo.entity.TokenLog;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.TokenLogRepository;
import com.example.demo.repository.TokenRepository;
import com.example.demo.service.TokenLogService;

@Service
public class TokenLogServiceImpl implements TokenLogService {

    private final TokenLogRepository tokenLogRepository;
    private final TokenRepository tokenRepository;

    public TokenLogServiceImpl(TokenLogRepository tokenLogRepository,
                               TokenRepository tokenRepository) {
        this.tokenLogRepository = tokenLogRepository;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public List<TokenLog> getLogs(Long tokenId) {
        return tokenLogRepository.findByTokenId(tokenId);
    }

    @Override
    public TokenLog saveLog(Long tokenId, String message) {

        Token token = tokenRepository.findById(tokenId)
                .orElseThrow(() -> new ResourceNotFoundException("Token not found"));

        TokenLog log = new TokenLog(
                token,
                message,
                LocalDateTime.now()
        );

        return tokenLogRepository.save(log);
    }

    // ✅ This is the correct place
    @Override
    public TokenLog addLog(Long tokenId, String message) {
        return saveLog(tokenId, message);
    }
}
