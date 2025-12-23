package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tokenNumber;

    private String status;

    @ManyToOne
    private ServiceCounter serviceCounter;

    private LocalDateTime issuedAt;

    private LocalDateTime completedAt;

    // ✅ REQUIRED BY JPA
    public Token() {
    }

    // ✅ REQUIRED BY SERVICE + TEST FILE
    public Token(String tokenNumber,
                 ServiceCounter serviceCounter,
                 String status,
                 LocalDateTime issuedAt) {
        this.tokenNumber = tokenNumber;
        this.serviceCounter = serviceCounter;
        this.status = status;
        this.issuedAt = issuedAt;
    }

    // ===== GETTERS =====
    public Long getId() {
        return id;
    }

    public String getTokenNumber() {
        return tokenNumber;
    }

    public String getStatus() {
        return status;
    }

    public ServiceCounter getServiceCounter() {
        return serviceCounter;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    // ===== SETTERS REQUIRED BY TEST FILE =====
    public void setId(Long id) {
        this.id = id;
    }

    public void setTokenNumber(String tokenNumber) {
        this.tokenNumber = tokenNumber;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setServiceCounter(ServiceCounter serviceCounter) {
        this.serviceCounter = serviceCounter;
    }

    public void setIssuedAt(LocalDateTime issuedAt) {
        this.issuedAt = issuedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}
