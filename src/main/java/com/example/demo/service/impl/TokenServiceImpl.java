package com.example.demo.service.impl;

import com.example.demo.entity.Token;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.TokenRepository;
import com.example.demo.service.TokenService;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Token issueToken(Long serviceCounterId) {
        Token token = new Token(serviceCounterId, "ISSUED");
        return tokenRepository.save(token);
    }

    @Override
    public Token updateStatus(Long tokenId, String status) {
        Token token = tokenRepository.findById(tokenId)
                .orElseThrow(() -> new ResourceNotFoundException("Token not found"));

        token.setStatus(status);
        return tokenRepository.save(token);
    }

    @Override
    public Token getToken(Long tokenId) {
        return tokenRepository.findById(tokenId)
                .orElseThrow(() -> new ResourceNotFoundException("Token not found"));
    }
}
