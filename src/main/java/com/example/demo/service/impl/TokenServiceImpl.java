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
import com.example.demo.service.TokenService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TokenServiceImpl implements TokenService {
    private final TokenRepository tokenRepository;
    private final ServiceCounterRepository counterRepository;
    private final TokenLogRepository logRepository;
    private final QueuePositionRepository queueRepository;

    public TokenServiceImpl(TokenRepository tokenRepository, ServiceCounterRepository counterRepository, 
                           TokenLogRepository logRepository, QueuePositionRepository queueRepository) {
        this.tokenRepository = tokenRepository;
        this.counterRepository = counterRepository;
        this.logRepository = logRepository;
        this.queueRepository = queueRepository;
    }

    @Override
    public Token issueToken(Long counterId) {
        ServiceCounter counter = counterRepository.findById(counterId)
            .orElseThrow(() -> new RuntimeException("Counter not found"));
        
        if (!counter.getIsActive()) {
            throw new IllegalArgumentException("Counter is not active");
        }

        List<Token> waitingTokens = tokenRepository.findByServiceCounter_IdAndStatusOrderByIssuedAtAsc(counterId, "WAITING");
        
        Token token = new Token();
        token.setTokenNumber(counter.getCounterName() + "-" + (waitingTokens.size() + 1));
        token.setServiceCounter(counter);
        token.setStatus("WAITING");
        token = tokenRepository.save(token);

        QueuePosition queuePosition = new QueuePosition();
        queuePosition.setToken(token);
        queuePosition.setPosition(waitingTokens.size() + 1);
        queueRepository.save(queuePosition);

        TokenLog log = new TokenLog();
        log.setToken(token);
        log.setMessage("Token issued");
        logRepository.save(log);

        return token;
    }

    @Override
    public Token updateStatus(Long tokenId, String newStatus) {
        Token token = tokenRepository.findById(tokenId)
            .orElseThrow(() -> new RuntimeException("Token not found"));

        if ("WAITING".equals(token.getStatus()) && "COMPLETED".equals(newStatus)) {
            throw new IllegalArgumentException("Invalid status transition");
        }

        token.setStatus(newStatus);
        if ("COMPLETED".equals(newStatus) || "CANCELLED".equals(newStatus)) {
            token.setCompletedAt(LocalDateTime.now());
        }
        
        token = tokenRepository.save(token);

        TokenLog log = new TokenLog();
        log.setToken(token);
        log.setMessage("Status updated to " + newStatus);
        logRepository.save(log);

        return token;
    }

    @Override
    public Token getToken(Long tokenId) {
        return tokenRepository.findById(tokenId)
            .orElseThrow(() -> new RuntimeException("Token not found"));
    }

    @Override
    public List<Token> getTokensByCounter(Long counterId, String status) {
        return tokenRepository.findByServiceCounter_IdAndStatusOrderByIssuedAtAsc(counterId, status);
    }
}