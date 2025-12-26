// package com.example.demo.service.impl;

// import com.example.demo.entity.QueuePosition;
// import com.example.demo.entity.Token;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.repository.QueuePositionRepository;
// import com.example.demo.repository.TokenRepository;
// import com.example.demo.service.QueueService;
// import org.springframework.stereotype.Service;

// import java.time.LocalDateTime;
// @Service
// public class QueueServiceImpl implements QueueService {

//     private final QueuePositionRepository queueRepository;
//     private final TokenRepository tokenRepository;

//     public QueueServiceImpl(QueuePositionRepository queueRepository,
//                             TokenRepository tokenRepository) {
//         this.queueRepository = queueRepository;
//         this.tokenRepository = tokenRepository;
//     }

//     public QueuePosition updateQueuePosition(Long tokenId, Integer newPosition) {
//         if (newPosition < 1) {
//             throw new IllegalArgumentException("1");
//         }

//         Token token = tokenRepository.findById(tokenId)
//                 .orElseThrow(() -> new ResourceNotFoundException("Token not found"));

//         QueuePosition qp = queueRepository.findByToken_Id(tokenId)
//                 .orElse(new QueuePosition(token, newPosition, LocalDateTime.now()));

//         qp.setPosition(newPosition);
//         qp.setUpdatedAt(LocalDateTime.now());
//         return queueRepository.save(qp);
//     }

//     public QueuePosition getPosition(Long tokenId) {
//         return queueRepository.findByToken_Id(tokenId)
//                 .orElseThrow(() -> new ResourceNotFoundException("Token not found"));
//     }
// }


// package com.example.demo.service.impl;

// import com.example.demo.entity.QueuePosition;
// import com.example.demo.entity.Token;
// import com.example.demo.repository.QueuePositionRepository;
// import com.example.demo.repository.TokenRepository;

// public class QueueServiceImpl {

//     private final QueuePositionRepository repo;
//     private final TokenRepository tokenRepo;

//     public QueueServiceImpl(QueuePositionRepository repo, TokenRepository tokenRepo) {
//         this.repo = repo;
//         this.tokenRepo = tokenRepo;
//     }

//     public QueuePosition updateQueuePosition(Long tokenId, Integer pos) {
//         if (pos < 1) throw new IllegalArgumentException(">= 1");

//         Token token = tokenRepo.findById(tokenId)
//                 .orElseThrow(() -> new RuntimeException("not found"));

//         QueuePosition qp = new QueuePosition();
//         qp.setToken(token);
//         qp.setPosition(pos);
//         return repo.save(qp);
//     }

//     public QueuePosition getPosition(Long tokenId) {
//         return repo.findByToken_Id(tokenId)
//                 .orElseThrow(() -> new RuntimeException("not found"));
//     }
// }

// package com.example.demo.service.impl;

// import com.example.demo.entity.QueuePosition;
// import com.example.demo.entity.Token;
// import com.example.demo.repository.QueuePositionRepository;
// import com.example.demo.repository.TokenRepository;

// import java.time.LocalDateTime;

// public class QueueServiceImpl {

//     private final QueuePositionRepository repo;
//     private final TokenRepository tokenRepo;

//     public QueueServiceImpl(QueuePositionRepository repo, TokenRepository tokenRepo) {
//         this.repo = repo;
//         this.tokenRepo = tokenRepo;
//     }

//     public QueuePosition updateQueuePosition(Long tokenId, Integer pos) {
//         if (pos < 1) {
//             throw new IllegalArgumentException(">= 1");
//         }

//         Token token = tokenRepo.findById(tokenId)
//                 .orElseThrow(() -> new RuntimeException("not found"));

//         QueuePosition qp = new QueuePosition();
//         qp.setToken(token);
//         qp.setPosition(pos);
//         qp.setUpdatedAt(LocalDateTime.now());

//         return repo.save(qp);
//     }

//     public QueuePosition getPosition(Long tokenId) {
//         return repo.findByToken_Id(tokenId)
//                 .orElseThrow(() -> new RuntimeException("not found"));
//     }
// }

package com.example.demo.service.impl;

import com.example.demo.entity.Token;
import com.example.demo.entity.TokenLog;
import com.example.demo.repository.TokenLogRepository;
import com.example.demo.repository.TokenRepository;

import java.util.List;

public class TokenLogServiceImpl {

    private final TokenLogRepository repo;
    private final TokenRepository tokenRepo;

    public TokenLogServiceImpl(TokenLogRepository repo, TokenRepository tokenRepo) {
        this.repo = repo;
        this.tokenRepo = tokenRepo;
    }

    public TokenLog addLog(Long tokenId, String msg) {
        Token token = tokenRepo.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("not found"));

        TokenLog log = new TokenLog();
        log.setToken(token);
        log.setLogMessage(msg);

        repo.save(log);
        return log;
    }

    public List<TokenLog> getLogs(Long tokenId) {
        return repo.findByToken_IdOrderByLoggedAtAsc(tokenId);
    }
}
