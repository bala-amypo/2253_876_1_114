package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cold_rooms")
public class ColdRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private Double minAllowed;
    private Double maxAllowed;

    public ColdRoom() {}

    public ColdRoom(String name, String location, Double minAllowed, Double maxAllowed) {
        this.name = name;
        this.location = location;
        this.minAllowed = minAllowed;
        this.maxAllowed = maxAllowed;
    }
    public Double getMinAllowed() {
    return minAllowed;
}

public Double getMaxAllowed() {
    return maxAllowed;
}


    // getters & setters
}
