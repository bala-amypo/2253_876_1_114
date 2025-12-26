// package com.example.demo.service.impl;

// import com.example.demo.entity.*;
// import com.example.demo.repository.*;
// import com.example.demo.service.TokenService;
// import org.springframework.stereotype.Service;

// import java.time.LocalDateTime;
// import java.util.List;

// @Service
// public class TokenServiceImpl implements TokenService {

//     private final TokenRepository tokenRepo;
//     private final ServiceCounterRepository counterRepo;
//     private final QueuePositionRepository queueRepo;
//     private final TokenLogRepository logRepo;

//     public TokenServiceImpl(
//             TokenRepository tokenRepo,
//             ServiceCounterRepository counterRepo,
//             QueuePositionRepository queueRepo,
//             TokenLogRepository logRepo) {
//         this.tokenRepo = tokenRepo;
//         this.counterRepo = counterRepo;
//         this.queueRepo = queueRepo;
//         this.logRepo = logRepo;
//     }

//     @Override
//     public Token issueToken(Long counterId) {
//         ServiceCounter counter = counterRepo.findById(counterId)
//                 .orElseThrow(() -> new RuntimeException("Counter not found"));

//         List<Token> waiting =
//                 tokenRepo.findByServiceCounter_IdAndStatusOrderByIssuedAtAsc(counterId, "WAITING");

//         Token token = new Token();
//         token.setServiceCounter(counter);
//         token.setStatus("WAITING");
//         token.setIssuedAt(LocalDateTime.now());
//         token.setTokenNumber(counter.getCounterName() + "-" + (waiting.size() + 1));

//         token = tokenRepo.save(token);

//         QueuePosition qp = new QueuePosition();
//         qp.setToken(token);
//         qp.setPosition(waiting.size() + 1);
//         queueRepo.save(qp);

//         TokenLog log = new TokenLog();
//         log.setToken(token);
//         log.setMessage("Token issued");
//         logRepo.save(log);

//         return token;
//     }

//     @Override
//     public Token updateStatus(Long tokenId, String status) {
//         Token token = getToken(tokenId);

//         token.setStatus(status);
//         if ("COMPLETED".equals(status)) {
//             token.setCompletedAt(LocalDateTime.now());
//         }

//         token = tokenRepo.save(token);

//         TokenLog log = new TokenLog();
//         log.setToken(token);
//         log.setMessage("Status changed to " + status);
//         logRepo.save(log);

//         return token;
//     }

//     @Override
//     public Token getToken(Long tokenId) {
//         return tokenRepo.findById(tokenId)
//                 .orElseThrow(() -> new RuntimeException("Token not found"));
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

    public Token issueToken(Long counterId) {
        ServiceCounter counter = counterRepository.findById(counterId)
            .orElseThrow(() -> new RuntimeException("Counter not found"));
        
        if (!counter.getIsActive()) {
            throw new IllegalArgumentException("Counter is not active");
        }

        Token token = new Token();
        token.setServiceCounter(counter);
        token.setStatus("WAITING");
        token.setIssuedAt(LocalDateTime.now());
        
        List<Token> waitingTokens = tokenRepository.findByServiceCounter_IdAndStatusOrderByIssuedAtAsc(counterId, "WAITING");
        token.setTokenNumber(counter.getCounterName() + "-" + (waitingTokens.size() + 1));
        
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

    public Token updateStatus(Long tokenId, String newStatus) {
        Token token = tokenRepository.findById(tokenId)
            .orElseThrow(() -> new RuntimeException("Token not found"));
        
        String currentStatus = token.getStatus();
        
        if ("WAITING".equals(currentStatus) && "COMPLETED".equals(newStatus)) {
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

    public Token getToken(Long tokenId) {
        return tokenRepository.findById(tokenId)
            .orElseThrow(() -> new RuntimeException("Token not found"));
    }
}