package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(
    name = "sensor_devices",
    uniqueConstraints = @UniqueConstraint(columnNames = "identifier")
)
public class SensorDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identifier;

    @ManyToOne
    @JoinColumn(name = "cold_room_id")
    private ColdRoom coldRoom;

    private Boolean isActive;

    // ✅ No-arg constructor
    public SensorDevice() {
    }

    // ✅ Parameterized constructor
    public SensorDevice(String identifier,
                        ColdRoom coldRoom,
                        Boolean isActive) {
        this.identifier = identifier;
        this.coldRoom = coldRoom;
        this.isActive = isActive;
    }

    // ✅ Getters and setters

    public Long getId() {
        return id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public ColdRoom getColdRoom() {
        return coldRoom;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setColdRoom(ColdRoom coldRoom) {
        this.coldRoom = coldRoom;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
