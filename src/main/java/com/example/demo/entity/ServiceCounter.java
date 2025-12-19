package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "service_counters")
public class ServiceCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Boolean active;

    public ServiceCounter() {}
    
    // getters + setters
}
