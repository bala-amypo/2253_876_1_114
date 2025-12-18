package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(
    name = "breach_alerts",
    uniqueConstraints = @UniqueConstraint(columnNames = "tokenNumber")
)
public class BreachAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tokenNumber;

    @ManyToOne
    @JoinColumn(name = "cold_room_id")
    private ColdRoom coldRoom;

    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private SensorDevice sensor;

    @ManyToOne
    @JoinColumn(name = "reading_id")
    private TemperatureReading reading;

    private String status;

    private String breachType;

    private LocalDateTime issuedAt;

    private LocalDateTime resolvedAt;

    // ✅ No-arg constructor
    public BreachAlert() {
    }

    // ✅ Parameterized constructor (REQUIRED)
    public BreachAlert(String tokenNumber,
                       ColdRoom coldRoom,
                       SensorDevice sensor,
                       TemperatureReading reading,
                       String status,
                       String breachType,
                       LocalDateTime issuedAt,
                       LocalDateTime resolvedAt) {
        this.tokenNumber = tokenNumber;
        this.coldRoom = coldRoom;
        this.sensor = sensor;
        this.reading = reading;
        this.status = status;
        this.breachType = breachType;
        this.issuedAt = issuedAt;
        this.resolvedAt = resolvedAt;
    }

    // ✅ Getters & Setters

    public Long getId() {
        return id;
    }

    public String getTokenNumber() {
        return tokenNumber;
    }

    public ColdRoom getColdRoom() {
        return coldRoom;
    }

    public SensorDevice getSensor() {
        return sensor;
    }

    public TemperatureReading getReading() {
        return reading;
    }

    public String getStatus() {
        return status;
    }

    public String getBreachType() {
        return breachType;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    public LocalDateTime getResolvedAt() {
        return resolvedAt;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResolvedAt(LocalDateTime resolvedAt) {
        this.resolvedAt = resolvedAt;
    }
}
