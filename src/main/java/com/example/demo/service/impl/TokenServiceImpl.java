package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.entity.BreachAlert;
import com.example.demo.entity.QueuePosition;
import com.example.demo.entity.TemperatureReading;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.QueuePositionRepository;
import com.example.demo.repository.TokenRepository;
import com.example.demo.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;
    private final QueuePositionRepository queueRepository;

    public TokenServiceImpl(TokenRepository tokenRepository,
                            QueuePositionRepository queueRepository) {
        this.tokenRepository = tokenRepository;
        this.queueRepository = queueRepository;
    }

    @Override
    public BreachAlert createBreachAlert(TemperatureReading reading,
                                         String breachType) {

        String tokenNumber = UUID.randomUUID().toString();

        BreachAlert alert = new BreachAlert(
                tokenNumber,
                reading.getColdRoom(),
                reading.getSensor(),
                reading,
                "OPEN",
                breachType,
                LocalDateTime.now(),
                null
        );

        tokenRepository.save(alert);

        // ✅ Create queue position
        QueuePosition queue = new QueuePosition(
                alert,
                1,
                LocalDateTime.now()
        );
        queueRepository.save(queue);

        return alert;
    }

    @Override
    public BreachAlert updateStatus(Long tokenId, String newStatus) {

        BreachAlert alert = tokenRepository.findById(tokenId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Token not found"));

        String oldStatus = alert.getStatus();

        // ✅ Status transition validation
        if (!isValidTransition(oldStatus, newStatus)) {
            throw new IllegalArgumentException("Invalid status transition");
        }

        alert.setStatus(newStatus);

        // ✅ Terminal states
        if (newStatus.equals("RESOLVED")
                || newStatus.equals("CANCELLED")) {
            alert.setResolvedAt(LocalDateTime.now());
        }

        return tokenRepository.save(alert);
    }

    @Override
    public BreachAlert getToken(Long tokenId) {
        return tokenRepository.findById(tokenId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Token not found"));
    }

    // ✅ Allowed transitions
    private boolean isValidTransition(String oldStatus,
                                      String newStatus) {

        if (oldStatus.equals("OPEN")) {
            return newStatus.equals("ACKNOWLEDGED")
                    || newStatus.equals("CANCELLED");
        }

        if (oldStatus.equals("ACKNOWLEDGED")) {
            return newStatus.equals("RESOLVED")
                    || newStatus.equals("CANCELLED");
        }

        return false;
    }
}
