package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "queue_positions")
public class QueuePosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long tokenId;

    private Integer position;

    public QueuePosition() {}

    public QueuePosition(Long tokenId, Integer position) {
        this.tokenId = tokenId;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public Long getTokenId() {
        return tokenId;
    }

    public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
