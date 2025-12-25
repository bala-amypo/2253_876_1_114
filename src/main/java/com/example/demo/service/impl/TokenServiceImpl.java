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

import com.example.demo.entity.QueuePosition;
import com.example.demo.entity.ServiceCounter;
import com.example.demo.entity.Token;
import com.example.demo.entity.TokenLog;
import com.example.demo.repository.QueuePositionRepository;
import com.example.demo.repository.ServiceCounterRepository;
import com.example.demo.repository.TokenLogRepository;
import com.example.demo.repository.TokenRepository;
import com.example.demo.service.TokenService;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class TokenServiceImpl {

    private final TokenRepository tokenRepository;
    private final ServiceCounterRepository counterRepository;
    private final TokenLogRepository logRepo;
    private final QueuePositionRepository queueRepo;

    public TokenServiceImpl(TokenRepository tokenRepository,
                            ServiceCounterRepository counterRepository,
                            TokenLogRepository logRepo,
                            QueuePositionRepository queueRepo) {
        this.tokenRepository = tokenRepository;
        this.counterRepository = counterRepository;
        this.logRepo = logRepo;
        this.queueRepo = queueRepo;
    }

    public Token issueToken(Long counterId) {

        ServiceCounter counter = counterRepository.findById(counterId)
                .orElseThrow(() -> new RuntimeException("Counter not found"));

        if (!Boolean.TRUE.equals(counter.getIsActive())) {
            throw new IllegalArgumentException("Counter not active");
        }

        List<Token> waiting =
                tokenRepository.findByServiceCounter_IdAndStatusOrderByIssuedAtAsc(counterId, "WAITING");

        if (waiting == null) {
            waiting = List.of();
        }

        Token token = new Token();
        token.setServiceCounter(counter);
        token.setStatus("WAITING");
        token.setIssuedAt(LocalDateTime.now());
        token.setTokenNumber(counter.getCounterName() + "-" + (waiting.size() + 1));

        token = tokenRepository.save(token);

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

        Token token = tokenRepository.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("Token not found"));

        String current = token.getStatus();

        boolean valid =
                (current.equals("WAITING") && newStatus.equals("SERVING")) ||
                (current.equals("SERVING") && newStatus.equals("COMPLETED")) ||
                ((current.equals("WAITING") || current.equals("SERVING"))
                        && newStatus.equals("CANCELLED"));

        if (!valid) {
            throw new IllegalArgumentException("Invalid status");
        }

        token.setStatus(newStatus);

        if (newStatus.equals("COMPLETED") || newStatus.equals("CANCELLED")) {
            token.setCompletedAt(LocalDateTime.now());
        }

        token = tokenRepository.save(token);

        TokenLog log = new TokenLog();
        log.setToken(token);
        log.setMessage("Status changed to " + newStatus);
        logRepo.save(log);

        return token;
    }

    public Token getToken(Long tokenId) {
        return tokenRepository.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("Token not found"));
    }
}
