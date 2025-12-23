package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TokenLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Token token;

    private String logMessage;

    private LocalDateTime loggedAt;

    // ✅ REQUIRED BY JPA
    public TokenLog() {
    }

    // ✅ REQUIRED BY SERVICE + TEST FILE
    public TokenLog(Token token, String logMessage) {
        this.token = token;
        this.logMessage = logMessage;
    }

    // ===== GETTERS =====
    public Long getId() {
        return id;
    }

    public Token getToken() {
        return token;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public LocalDateTime getLoggedAt() {
        return loggedAt;
    }

    // ===== SETTERS REQUIRED BY TEST FILE =====
    public void setId(Long id) {
        this.id = id;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    public void setLoggedAt(LocalDateTime loggedAt) {
        this.loggedAt = loggedAt;
    }

    // Optional but safe
    @PrePersist
    public void onCreate() {
        this.loggedAt = LocalDateTime.now();
    }
}
