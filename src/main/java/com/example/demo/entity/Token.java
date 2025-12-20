package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tokens")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long serviceCounterId;

    private String status; // e.g., "ISSUED", "SERVED"

    public Token() {}

    public Token(Long serviceCounterId, String status) {
        this.serviceCounterId = serviceCounterId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getServiceCounterId() {
        return serviceCounterId;
    }

    public void setServiceCounterId(Long serviceCounterId) {
        this.serviceCounterId = serviceCounterId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
