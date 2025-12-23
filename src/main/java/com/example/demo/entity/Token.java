package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tokens", uniqueConstraints = @UniqueConstraint(columnNames = "tokenNumber"))
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tokenNumber;

    @ManyToOne
    private ServiceCounter serviceCounter;

    private String status;

    private LocalDateTime issuedAt;
    private LocalDateTime completedAt;

    public Token() {}

    public Token(String tokenNumber, ServiceCounter serviceCounter, String status, LocalDateTime issuedAt) {
        this.tokenNumber = tokenNumber;
        this.serviceCounter = serviceCounter;
        this.status = status;
        this.issuedAt = issuedAt;
    }

    // getters & setters
}
