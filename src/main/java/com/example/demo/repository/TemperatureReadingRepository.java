package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TemperatureReading;

@Repository
public interface TemperatureReadingRepository extends JpaRepository<TemperatureReading, Long> {
}
