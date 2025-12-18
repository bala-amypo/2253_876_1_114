package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "queue_positions")
public class QueuePosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "token_id")
    private BreachAlert token;

    private Integer position;

    private LocalDateTime updatedAt;

    // ✅ No-arg constructor
    public QueuePosition() {
    }

    // ✅ Parameterized constructor
    public QueuePosition(BreachAlert token,
                         Integer position,
                         LocalDateTime updatedAt) {
        this.token = token;
        this.position = position;
        this.updatedAt = updatedAt;
    }

    // ✅ Getters & Setters

    public Long getId() {
        return id;
    }

    public BreachAlert getToken() {
        return token;
    }

    public Integer getPosition() {
        return position;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setToken(BreachAlert token) {
        this.token = token;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
