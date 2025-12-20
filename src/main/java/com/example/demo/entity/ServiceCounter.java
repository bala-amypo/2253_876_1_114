package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "service_counters")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String counterName;

    private String department;

    @Column(name = "active")
    private Boolean isActive;
}
