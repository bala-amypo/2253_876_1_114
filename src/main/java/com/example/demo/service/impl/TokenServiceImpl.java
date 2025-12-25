package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TokenServiceImpl {

    private final TokenRepository tokenRepo;
    private final ServiceCounterRepository counterRepo;
    private final TokenLogRepository logRepo;
    private final QueuePositionRepository queueRepo;

    public TokenServiceImpl(TokenRepository tokenRepo,
                            ServiceCounterRepository counterRepo,
                            TokenLogRepository logRepo,
                            QueuePositionRepository queueRepo) {
        this.tokenRepo = tokenRepo;
        this.counterRepo = counterRepo;
        this.logRepo = logRepo;
        this.queueRepo = queueRepo;
    }

    public Token issueToken(Long counterId) {
        ServiceCounter counter = counterRepo.findById(counterId)
                .orElseThrow(() -> new RuntimeException("Counter not found"));

        if (!Boolean.TRUE.equals(counter.getIsActive())) {
            throw new IllegalArgumentException("Counter not active");
        }

        List<Token> waiting =
                tokenRepo.findByServiceCounter_IdAndStatusOrderByIssuedAtAsc(counterId, "WAITING");

        Token token = new Token();
        token.setServiceCounter(counter);
        token.setStatus("WAITING");
        token.setIssuedAt(LocalDateTime.now());
        token.setTokenNumber(counter.getCounterName() + "-" + (waiting.size() + 1));

        token = tokenRepo.save(token);

        QueuePosition qp = new QueuePosition();
        qp.setToken(token);
        qp.setPosition(waiting.size() + 1);
        queueRepo.save(qp);

        TokenLog log = new TokenLog();
        log.setToken(token);
        log.setMessage("Token issued");
        logRepo.save(log);

        return token;
    }

    public Token updateStatus(Long tokenId, String newStatus) {
        Token token = tokenRepo.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("Token not found"));

        String cur = token.getStatus();

        boolean valid =
                (cur.equals("WAITING") && newStatus.equals("SERVING")) ||
                (cur.equals("SERVING") && newStatus.equals("COMPLETED")) ||
                ((cur.equals("WAITING") || cur.equals("SERVING")) && newStatus.equals("CANCELLED"));

        if (!valid) {
            throw new IllegalArgumentException("Invalid status");
        }

        token.setStatus(newStatus);

        if (newStatus.equals("COMPLETED") || newStatus.equals("CANCELLED")) {
            token.setCompletedAt(LocalDateTime.now());
        }

        token = tokenRepo.save(token);

        TokenLog log = new TokenLog();
        log.setToken(token);
        log.setMessage("Status changed to " + newStatus);
        logRepo.save(log);

        return token;
    }

    public Token getToken(Long tokenId) {
        return tokenRepo.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("Token not found"));
    }
}
