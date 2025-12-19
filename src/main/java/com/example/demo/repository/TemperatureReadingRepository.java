package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.TemperatureReading;

// public interface TemperatureReadingRepository
//         extends JpaRepository<TemperatureReading, Long> {

//     List<TemperatureReading> findByColdRoomId(Long coldRoomId);
// }
@Repository
public interface TemperatureReadingRepository extends JpaRepository<TemperatureReading, Long> {
}
