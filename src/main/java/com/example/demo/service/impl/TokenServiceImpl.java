package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;



import com.example.demo.entity.Queue;
import com.example.demo.entity.Token;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.QueueRepository;
import com.example.demo.repository.TokenRepository;
import com.example.demo.service.TokenLogService;
import com.example.demo.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;
    private final QueueRepository queueRepository;
    private final TokenLogService tokenLogService;

    public TokenServiceImpl(TokenRepository tokenRepository,
                            QueueRepository queueRepository,
                            TokenLogService tokenLogService) {

        this.tokenRepository = tokenRepository;
        this.queueRepository = queueRepository;
        this.tokenLogService = tokenLogService;
    }

    // ================= CREATE TOKEN =====================
    @Override
    public Token createToken(Long queueId) {

        Queue queue = queueRepository.findById(queueId)
                .orElseThrow(() -> new ResourceNotFoundException("Queue not found"));

        Token token = new Token();
        token.setQueue(queue);
        token.setStatus("PENDING");
        token.setCreatedAt(LocalDateTime.now());

        Token saved = tokenRepository.save(token);

        // LOG
        tokenLogService.addLog(saved.getId(), "Token Created");

        return saved;
    }

    // ================= GET ALL TOKENS =====================
    @Override
    public List<Token> getTokens() {
        return tokenRepository.findAll();
    }

    // ================= UPDATE STATUS =====================
    @Override
    public Token updateStatus(Long tokenId, String status) {

        Token token = tokenRepository.findById(tokenId)
                .orElseThrow(() -> new ResourceNotFoundException("Token not found"));

        token.setStatus(status);
        Token saved = tokenRepository.save(token);

        // LOG
        tokenLogService.addLog(saved.getId(),
                "Token status changed to " + saved.getStatus());

        return saved;
    }

    // ================= DELETE TOKEN =====================
    @Override
    public void deleteToken(Long id) {

        Token token = tokenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Token not found"));

        tokenRepository.delete(token);

        // LOG
        tokenLogService.addLog(id, "Token Deleted");
    }
}
