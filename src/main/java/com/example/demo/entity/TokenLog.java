package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "token_logs")
public class TokenLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "token_id")
    private BreachAlert token;

    private String logMessage;

    private LocalDateTime loggedAt;

    // ✅ No-arg constructor
    public TokenLog() {
    }

    // ✅ Parameterized constructor (required)
    public TokenLog(BreachAlert token,
                    String logMessage,
                    LocalDateTime loggedAt) {
        this.token = token;
        this.logMessage = logMessage;
        this.loggedAt = loggedAt;
    }

    // Auto set time
    @PrePersist
    public void onCreate() {
        if (loggedAt == null) {
            loggedAt = LocalDateTime.now();
        }
    }

    // Getters
    public Long getId() {
        return id;
    }

    public BreachAlert getToken() {
        return token;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public LocalDateTime getLoggedAt() {
        return loggedAt;
    }

    public void setToken(BreachAlert token) {
        this.token = token;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }
}
