package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class ServiceCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String counterName;
    private String department;
    private Boolean isActive;

    public ServiceCounter() {}

    public ServiceCounter(String counterName, String department, Boolean isActive) {
        this.counterName = counterName;
        this.department = department;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public String getCounterName() {
        return counterName;
    }

    public String getDepartment() {
        return department;
    }

    public Boolean getIsActive() {
        return isActive;
    }
}
