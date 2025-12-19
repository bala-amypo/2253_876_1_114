package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.SensorDevice;

public interface SensorDeviceRepository extends JpaRepository<SensorDevice, Long> {
}
