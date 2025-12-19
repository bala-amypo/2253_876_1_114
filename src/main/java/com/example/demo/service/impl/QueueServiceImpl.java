package com.example.demo.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import com.example.demo.entity.Token;
import com.example.demo.entity.Queue;

import com.example.demo.entity.QueuePosition;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.QueuePositionRepository;
import com.example.demo.repository.TokenRepository;
import com.example.demo.service.QueueService;

@Service
public class QueueServiceImpl implements QueueService {

    private final QueuePositionRepository queueRepository;
    private final TokenRepository tokenRepository;

    public QueueServiceImpl(QueuePositionRepository queueRepository,
                            TokenRepository tokenRepository) {
        this.queueRepository = queueRepository;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public QueuePosition updateQueuePosition(Long tokenId,
                                             Integer newPosition) {

        // ✅ position must be >= 1
        if (newPosition < 1) {
            throw new IllegalArgumentException("Position must be >= 1");
        }

        BreachAlert token = tokenRepository.findById(tokenId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Token not found"));

        QueuePosition queue = queueRepository
                .findByTokenId(tokenId)
                .orElse(new QueuePosition(token, newPosition, LocalDateTime.now()));

        queue.setPosition(newPosition);
        queue.setUpdatedAt(LocalDateTime.now());

        return queueRepository.save(queue);
    }

    @Override
    public QueuePosition getPosition(Long tokenId) {

        return queueRepository.findByTokenId(tokenId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Queue not found"));
    }
}
