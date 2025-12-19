package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "tokens")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Token belongs to a Queue Position
    @ManyToOne
    @JoinColumn(name = "queue_position_id")
    private QueuePosition queuePosition;

    private String status;

    private LocalDateTime createdAt;

    public Token() {
    }

    public Token(QueuePosition queuePosition, String status, LocalDateTime createdAt) {
        this.queuePosition = queuePosition;
        this.status = status;
        this.createdAt = createdAt;
    }

    @PrePersist
    public void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public QueuePosition getQueuePosition() {
        return queuePosition;
    }

    public void setQueuePosition(QueuePosition queuePosition) {
        this.queuePosition = queuePosition;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
