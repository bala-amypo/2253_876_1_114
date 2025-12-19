package com.example.demo.service;

import com.example.demo.entity.Token;
import java.util.List;

public interface TokenService {

    Token createToken(Long queuePositionId);

    Token getToken(Long id);

    List<Token> getAllTokens();

    Token updateStatus(Long id, String status);

    void deleteToken(Long id);
}
