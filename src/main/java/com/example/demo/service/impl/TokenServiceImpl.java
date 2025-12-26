// package com.example.demo.service.impl;

// import com.example.demo.entity.*;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.repository.*;
// import com.example.demo.service.TokenService;
// import org.springframework.stereotype.Service;

// import java.time.LocalDateTime;
// import java.util.UUID;

// @Service
// public class TokenServiceImpl implements TokenService {

//     private final TokenRepository tokenRepository;
//     private final ServiceCounterRepository counterRepository;
//     private final TokenLogRepository logRepository;
//     private final QueuePositionRepository queueRepository;

//     public TokenServiceImpl(TokenRepository tokenRepository,
//                             ServiceCounterRepository counterRepository,
//                             TokenLogRepository logRepository,
//                             QueuePositionRepository queueRepository) {
//         this.tokenRepository = tokenRepository;
//         this.counterRepository = counterRepository;
//         this.logRepository = logRepository;
//         this.queueRepository = queueRepository;
//     }

//     public Token issueToken(Long counterId) {
//         ServiceCounter counter = counterRepository.findById(counterId)
//                 .orElseThrow(() -> new ResourceNotFoundException("Counter not found"));

//         if (!counter.getIsActive()) {
//             throw new IllegalArgumentException("not active");
//         }

//         Token token = new Token(
//                 UUID.randomUUID().toString(),
//                 counter,
//                 "WAITING",
//                 LocalDateTime.now()
//         );
//         token = tokenRepository.save(token);

//         queueRepository.save(new QueuePosition(token, 1, LocalDateTime.now()));
//         logRepository.save(new TokenLog(token, "Token issued"));

//         return token;
//     }

//     public Token updateStatus(Long tokenId, String status) {
//         Token token = getToken(tokenId);

//         if (token.getStatus().equals("WAITING") && status.equals("SERVING")
//                 || token.getStatus().equals("SERVING") && status.equals("COMPLETED")) {

//             token.setStatus(status);
//             if (status.equals("COMPLETED")) {
//                 token.setCompletedAt(LocalDateTime.now());
//             }
//             tokenRepository.save(token);
//             logRepository.save(new TokenLog(token, "Status updated to " + status));
//             return token;
//         }

//         throw new IllegalArgumentException("Invalid status");
//     }

//     public Token getToken(Long tokenId) {
//         return tokenRepository.findById(tokenId)
//                 .orElseThrow(() -> new ResourceNotFoundException("Token not found"));
//     }
// }

package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;

import java.time.LocalDateTime;
import java.util.UUID;

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
        ServiceCounter sc = counterRepo.findById(counterId)
                .orElseThrow(() -> new RuntimeException("not found"));

        if (!sc.getIsActive()) {
            throw new IllegalArgumentException("not active");
        }

        Token token = new Token();
        token.setServiceCounter(sc);
        token.setStatus("WAITING");
        token.setIssuedAt(LocalDateTime.now());
        token.setTokenNumber(UUID.randomUUID().toString());

        token = tokenRepo.save(token);

        // ✅ FIXED QueuePosition creation
        QueuePosition qp = new QueuePosition();
        qp.setToken(token);
        qp.setPosition(1);
        qp.setUpdatedAt(LocalDateTime.now());
        queueRepo.save(qp);

        // ✅ FIXED TokenLog creation
        TokenLog log = new TokenLog();
        log.setToken(token);
        log.setLogMessage("Issued");
        logRepo.save(log);

        return token;
    }

    public Token updateStatus(Long tokenId, String newStatus) {
        Token token = tokenRepo.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("not found"));

        String current = token.getStatus();

        boolean valid =
                (current.equals("WAITING") && newStatus.equals("SERVING")) ||
                (current.equals("SERVING") &&
                        (newStatus.equals("COMPLETED") || newStatus.equals("CANCELLED")));

        if (!valid) {
            throw new IllegalArgumentException("Invalid status");
        }

        token.setStatus(newStatus);

        if (newStatus.equals("COMPLETED") || newStatus.equals("CANCELLED")) {
            token.setCompletedAt(LocalDateTime.now());
        }

        tokenRepo.save(token);

        // ✅ FIXED TokenLog creation
        TokenLog log = new TokenLog();
        log.setToken(token);
        log.setLogMessage("Status changed to " + newStatus);
        logRepo.save(log);

        return token;
    }

    public Token getToken(Long id) {
        return tokenRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }
}
