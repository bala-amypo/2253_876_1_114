package com.example.demo.service.impl;

import com.example.demo.entity.Token;
import com.example.demo.entity.QueuePosition;
import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.QueuePositionRepository;
import com.example.demo.service.TokenService;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;
    private final QueuePositionRepository queuePositionRepository;

    public TokenServiceImpl(TokenRepository tokenRepository,
                            QueuePositionRepository queuePositionRepository) {
        this.tokenRepository = tokenRepository;
        this.queuePositionRepository = queuePositionRepository;
    }

    @Override
    public Token createToken(Long queuePositionId) {

        QueuePosition qp = queuePositionRepository.findById(queuePositionId)
                .orElseThrow(() -> new RuntimeException("Queue Position Not Found"));

        Token token = new Token();
        token.setQueuePosition(qp);
        token.setStatus("ACTIVE");
        return tokenRepository.save(token);
    }

    @Override
    public Token getToken(Long id) {
        return tokenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Token Not Found"));
    }

    @Override
    public List<Token> getAllTokens() {
        return tokenRepository.findAll();
    }

    @Override
    public Token updateStatus(Long id, String status) {
        Token token = getToken(id);
        token.setStatus(status);
        return tokenRepository.save(token);
    }

    @Override
    public void deleteToken(Long id) {
        tokenRepository.deleteById(id);
    }
}
