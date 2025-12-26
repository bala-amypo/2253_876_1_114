// // // package com.example.demo.service.impl;

// // // import com.example.demo.entity.QueuePosition;
// // // import com.example.demo.entity.Token;
// // // import com.example.demo.exception.ResourceNotFoundException;
// // // import com.example.demo.repository.QueuePositionRepository;
// // // import com.example.demo.repository.TokenRepository;
// // // import com.example.demo.service.QueueService;
// // // import org.springframework.stereotype.Service;

// // // import java.time.LocalDateTime;

// // // @Service
// // // public class QueueServiceImpl implements QueueService {

// // //     private final QueuePositionRepository queueRepository;
// // //     private final TokenRepository tokenRepository;

// // //     public QueueServiceImpl(QueuePositionRepository queueRepository,
// // //                             TokenRepository tokenRepository) {
// // //         this.queueRepository = queueRepository;
// // //         this.tokenRepository = tokenRepository;
// // //     }

// // //     public QueuePosition updateQueuePosition(Long tokenId, Integer newPosition) {
// // //         if (newPosition < 1) {
// // //             throw new IllegalArgumentException("1");
// // //         }

// // //         Token token = tokenRepository.findById(tokenId)
// // //                 .orElseThrow(() -> new ResourceNotFoundException("Token not found"));

// // //         QueuePosition qp = queueRepository.findByToken_Id(tokenId)
// // //                 .orElse(new QueuePosition(token, newPosition, LocalDateTime.now()));

// // //         qp.setPosition(newPosition);
// // //         qp.setUpdatedAt(LocalDateTime.now());
// // //         return queueRepository.save(qp);
// // //     }

// // //     public QueuePosition getPosition(Long tokenId) {
// // //         return queueRepository.findByToken_Id(tokenId)
// // //                 .orElseThrow(() -> new ResourceNotFoundException("Token not found"));
// // //     }
// // // }

// // package com.example.demo.service.impl;

// // import com.example.demo.entity.QueuePosition;
// // import com.example.demo.entity.Token;
// // import com.example.demo.repository.QueuePositionRepository;
// // import com.example.demo.repository.TokenRepository;
// // import com.example.demo.service.QueueService;

// // import org.springframework.stereotype.Service;
// // @Service
// // public class QueueServiceImpl {

// //     private final QueuePositionRepository queueRepo;
// //     private final TokenRepository tokenRepository;

// //     public QueueServiceImpl(QueuePositionRepository queueRepo,
// //                             TokenRepository tokenRepository) {
// //         this.queueRepo = queueRepo;
// //         this.tokenRepository = tokenRepository;
// //     }

// //     public QueuePosition updateQueuePosition(Long tokenId, int position) {

// //         if (position < 1) {
// //             throw new IllegalArgumentException("Position must be >= 1");
// //         }

// //         Token token = tokenRepository.findById(tokenId)
// //                 .orElseThrow(() -> new RuntimeException("Token not found"));

// //         QueuePosition qp = new QueuePosition();   // ✅ ALWAYS NEW
// //         qp.setToken(token);
// //         qp.setPosition(position);

// //         return queueRepo.save(qp);                // ✅ NEVER NULL
// //     }

// //     public QueuePosition getPosition(Long tokenId) {
// //         return queueRepo.findByToken_Id(tokenId)
// //                 .orElseThrow(() -> new RuntimeException("Position not found"));
// //     }
// // // }
// // package com.example.demo.service.impl;

// // import com.example.demo.entity.QueuePosition;
// // import com.example.demo.entity.Token;
// // import com.example.demo.repository.QueuePositionRepository;
// // import com.example.demo.repository.TokenRepository;
// // import org.springframework.stereotype.Service;

// // @Service
// // public class QueueServiceImpl {

// //     private final QueuePositionRepository queueRepo;
// //     private final TokenRepository tokenRepository;

// //     public QueueServiceImpl(QueuePositionRepository queueRepo,
// //                             TokenRepository tokenRepository) {
// //         this.queueRepo = queueRepo;
// //         this.tokenRepository = tokenRepository;
// //     }

// //     public QueuePosition updateQueuePosition(Long tokenId, int position) {

// //         if (position < 1) {
// //             throw new IllegalArgumentException("Invalid position");
// //         }

// //         Token token = tokenRepository.findById(tokenId)
// //                 .orElseThrow(() -> new RuntimeException("Token not found"));

// //         QueuePosition qp = new QueuePosition();
// //         qp.setToken(token);
// //         qp.setPosition(position);

// // //         return queueRepo.save(qp); // ✅
// // //     }

// // //     public QueuePosition getPosition(Long tokenId) {
// // //         return queueRepo.findByToken_Id(tokenId)
// // //                 .orElseThrow(() -> new RuntimeException("Position not found"));
// // //     }
// // // }

// // package com.example.demo.service.impl;

// // import com.example.demo.entity.QueuePosition;
// // import com.example.demo.entity.Token;
// // import com.example.demo.repository.QueuePositionRepository;
// // import com.example.demo.repository.TokenRepository;
// // import com.example.demo.service.QueueService;
// // import org.springframework.stereotype.Service;

// // @Service
// // public class QueueServiceImpl implements QueueService {

// //     private final QueuePositionRepository queueRepo;
// //     private final TokenRepository tokenRepo;

// //     public QueueServiceImpl(QueuePositionRepository queueRepo,
// //                             TokenRepository tokenRepo) {
// //         this.queueRepo = queueRepo;
// //         this.tokenRepo = tokenRepo;
// //     }

// //     @Override
// //     public QueuePosition updateQueuePosition(Long tokenId, int position) {

// // //         if (position < 1) {
// // //             throw new IllegalArgumentException("Invalid position");
// // //         }

// // //         Token token = tokenRepo.findById(tokenId)
// // //                 .orElseThrow(() -> new RuntimeException("Token not found"));

// // //         QueuePosition qp = new QueuePosition();
// // //         qp.setToken(token);
// // //         qp.setPosition(position);

// // //         return queueRepo.save(qp);
// // //     }
// // // }

// // package com.example.demo.service.impl;

// // import com.example.demo.entity.QueuePosition;
// // import com.example.demo.entity.Token;
// // import com.example.demo.repository.QueuePositionRepository;
// // import com.example.demo.repository.TokenRepository;
// // import com.example.demo.service.QueueService;
// // import org.springframework.stereotype.Service;

// // @Service
// // public class QueueServiceImpl implements QueueService {

// //     private final QueuePositionRepository queueRepo;
// //     private final TokenRepository tokenRepo;

// //     public QueueServiceImpl(QueuePositionRepository queueRepo,
// //                             TokenRepository tokenRepo) {
// //         this.queueRepo = queueRepo;
// //         this.tokenRepo = tokenRepo;
// //     }

// //     @Override
// //     public QueuePosition updateQueuePosition(Long tokenId, int position) {

// //         if (position < 1) {
// //             throw new IllegalArgumentException("Invalid position");
// //         }

// //         Token token = tokenRepo.findById(tokenId)
// //                 .orElseThrow(() -> new RuntimeException("Token not found"));

// //         QueuePosition qp = new QueuePosition();
// //         qp.setToken(token);
// //         qp.setPosition(position);

// //         return queueRepo.save(qp);
// //     }

// //     @Override
// //     public QueuePosition getPosition(Long tokenId) {

// //         return queueRepo.findByToken_Id(tokenId)
// //                 .orElseThrow(() -> new RuntimeException("Queue position not found"));
// //     }
// // // }

// // package com.example.demo.service.impl;

// // import com.example.demo.entity.QueuePosition;
// // import com.example.demo.entity.Token;
// // import com.example.demo.repository.QueuePositionRepository;
// // import com.example.demo.repository.TokenRepository;
// // import com.example.demo.service.QueueService;
// // import org.springframework.stereotype.Service;

// // @Service
// // public class QueueServiceImpl implements QueueService {

// //     private final QueuePositionRepository queueRepo;
// //     private final TokenRepository tokenRepo;

// //     public QueueServiceImpl(QueuePositionRepository queueRepo,
// //                             TokenRepository tokenRepo) {
// //         this.queueRepo = queueRepo;
// //         this.tokenRepo = tokenRepo;
// //     }

// //     @Override
// //     public QueuePosition updateQueuePosition(Long tokenId, int position) {
// //         if (position < 1) {
// //             throw new IllegalArgumentException("Position must be >= 1");
// //         }

// //         Token token = tokenRepo.findById(tokenId)
// //                 .orElseThrow(() -> new RuntimeException("Token not found"));

// //         QueuePosition qp = new QueuePosition();
// //         qp.setToken(token);
// //         qp.setPosition(position);

// //         return queueRepo.save(qp);
// //     }

// //     @Override
// //     public QueuePosition getPosition(Long tokenId) {
// //         return queueRepo.findByToken_Id(tokenId)
// //                 .orElseThrow(() -> new RuntimeException("Queue position not found"));
// //     }
// // }

// package com.example.demo.service.impl;

// import com.example.demo.entity.QueuePosition;
// import com.example.demo.repository.QueuePositionRepository;
// import com.example.demo.service.QueueService;
// import org.springframework.stereotype.Service;

// @Service
// public class QueueServiceImpl implements QueueService {

//     private final QueuePositionRepository queueRepo;

//     public QueueServiceImpl(QueuePositionRepository queueRepo) {
//         this.queueRepo = queueRepo;
//     }

//     @Override
//     public QueuePosition getPosition(Long tokenId) {
//         return queueRepo.findByToken_Id(tokenId)
//                 .orElseThrow(() -> new RuntimeException("Queue position not found"));
//     }
// }

package com.example.demo.service.impl;

import com.example.demo.entity.QueuePosition;
import com.example.demo.entity.Token;
import com.example.demo.repository.QueuePositionRepository;
import com.example.demo.repository.TokenRepository;
import com.example.demo.service.QueueService;
import org.springframework.stereotype.Service;

@Service
public class QueueServiceImpl implements QueueService {
    private final QueuePositionRepository queueRepository;
    private final TokenRepository tokenRepository;

    public QueueServiceImpl(QueuePositionRepository queueRepository, TokenRepository tokenRepository) {
        this.queueRepository = queueRepository;
        this.tokenRepository = tokenRepository;
    }

    public QueuePosition updateQueuePosition(Long tokenId, Integer position) {
        if (position < 1) {
            throw new IllegalArgumentException("Position must be >= 1");
        }
        
        Token token = tokenRepository.findById(tokenId).orElse(null);
        
        QueuePosition queuePosition = queueRepository.findByToken_Id(tokenId)
            .orElse(new QueuePosition());
        
        queuePosition.setToken(token);
        queuePosition.setPosition(position);
        
        return queueRepository.save(queuePosition);
    }

    public QueuePosition getPosition(Long tokenId) {
        return queueRepository.findByToken_Id(tokenId).orElse(null);
    }
}
