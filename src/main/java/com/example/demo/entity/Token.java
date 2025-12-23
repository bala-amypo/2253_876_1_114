package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "tokens",
        uniqueConstraints = @UniqueConstraint(columnNames = "tokenNumber")
)
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

    public Long getId() {
        return id;
    }

    public String getTokenNumber() {
        return tokenNumber;
    }

    public ServiceCounter getServiceCounter() {
        return serviceCounter;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}
