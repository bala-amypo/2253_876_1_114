// package com.example.demo.service.impl;

// import com.example.demo.entity.Token;
// import com.example.demo.entity.TokenLog;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.repository.TokenLogRepository;
// import com.example.demo.repository.TokenRepository;
// import com.example.demo.service.TokenLogService;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class TokenLogServiceImpl implements TokenLogService {

//     private final TokenLogRepository logRepository;
//     private final TokenRepository tokenRepository;

//     public TokenLogServiceImpl(TokenLogRepository logRepository,
//                                TokenRepository tokenRepository) {
//         this.logRepository = logRepository;
//         this.tokenRepository = tokenRepository;
//     }

//     public TokenLog addLog(Long tokenId, String message) {
//         Token token = tokenRepository.findById(tokenId)
//                 .orElseThrow(() -> new ResourceNotFoundException("Token not found"));

//         return logRepository.save(new TokenLog(token, message));
//     }

//     public List<TokenLog> getLogs(Long tokenId) {
//         return logRepository.findByToken_IdOrderByLoggedAtAsc(tokenId);
//     }
// }

package com.example.demo.service.impl;

import com.example.demo.entity.Token;
import com.example.demo.entity.TokenLog;
import com.example.demo.repository.TokenLogRepository;
import com.example.demo.repository.TokenRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenLogServiceImpl {

    private final TokenLogRepository logRepo;
    private final TokenRepository tokenRepository;

    public TokenLogServiceImpl(TokenLogRepository logRepo,
                               TokenRepository tokenRepository) {
        this.logRepo = logRepo;
        this.tokenRepository = tokenRepository;
    }

    public TokenLog addLog(Long tokenId, String message) {
        Token token = tokenRepository.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("Token not found"));

        TokenLog log = new TokenLog();
        log.setToken(token);
        log.setMessage(message);

        return logRepo.save(log); // REQUIRED
    }

    public List<TokenLog> getLogs(Long tokenId) {
        return logRepo.findByToken_IdOrderByLoggedAtAsc(tokenId);
    }
}
