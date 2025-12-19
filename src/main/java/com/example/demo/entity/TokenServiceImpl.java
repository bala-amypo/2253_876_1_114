package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.QueuePosition;
import com.example.demo.entity.Token;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.QueuePositionRepository;
import com.example.demo.repository.TokenRepository;
import com.example.demo.service.TokenLogService;
import com.example.demo.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;
    private final QueuePositionRepository queuePositionRepository;
    private final TokenLogService tokenLogService;

    public TokenServiceImpl(TokenRepository tokenRepository,
                            QueuePositionRepository queuePositionRepository,
                            TokenLogService tokenLogService) {

        this.tokenRepository = tokenRepository;
        this.queuePositionRepository = queuePositionRepository;
        this.tokenLogService = tokenLogService;
    }

    @Override
    public Token createToken(Long queuePositionId) {

        QueuePosition queuePosition = queuePositionRepository.findById(queuePositionId)
                .orElseThrow(() -> new ResourceNotFoundException("Queue Position not found"));

        Token token = new Token();
        token.setQueuePosition(queuePosition);
        token.setStatus("PENDING");
        token.setCreatedAt(LocalDateTime.now());

        Token saved = tokenRepository.save(token);

        tokenLogService.addLog(saved.getId(), "Token Created");

        return saved;
    }

    @Override
    public Token getToken(Long id) {
        return tokenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Token not found"));
    }

    @Override
    public List<Token> getTokens() {
        return tokenRepository.findAll();
    }

    @Override
    public Token updateStatus(Long tokenId, String status) {

        Token token = tokenRepository.findById(tokenId)
                .orElseThrow(() -> new ResourceNotFoundException("Token not found"));

        token.setStatus(status);

        Token saved = tokenRepository.save(token);

        tokenLogService.addLog(saved.getId(),
                "Token status changed to " + saved.getStatus());

        return saved;
    }

    @Override
    public void deleteToken(Long id) {

        Token token = tokenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Token not found"));

        tokenRepository.delete(token);

        tokenLogService.addLog(id, "Token Deleted");
    }
}
