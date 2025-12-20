package com.example.demo.service.impl;

import com.example.demo.entity.QueuePosition;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.QueuePositionRepository;
import com.example.demo.service.QueueService;
import org.springframework.stereotype.Service;

@Service
public class QueueServiceImpl implements QueueService {

    private final QueuePositionRepository queueRepository;

    public QueueServiceImpl(QueuePositionRepository queueRepository) {
        this.queueRepository = queueRepository;
    }

    @Override
    public QueuePosition updateQueuePosition(Long tokenId, Integer newPosition) {

        QueuePosition position = queueRepository
                .findByTokenId(tokenId)
                .orElse(new QueuePosition(tokenId, newPosition));

        position.setPosition(newPosition);

        return queueRepository.save(position);
    }

    @Override
    public QueuePosition getPosition(Long tokenId) {
        return queueRepository.findByTokenId(tokenId)
                .orElseThrow(() -> new ResourceNotFoundException("Queue position not found"));
    }
}
