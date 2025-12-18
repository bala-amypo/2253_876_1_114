package com.example.demo.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.demo.entity.BreachAlert;
import com.example.demo.entity.ColdRoom;
import com.example.demo.entity.SensorDevice;
import com.example.demo.entity.TemperatureReading;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.TemperatureReadingRepository;
import com.example.demo.service.SensorService;
import com.example.demo.service.TemperatureReadingService;
import com.example.demo.service.TokenService;

@Service
public class TemperatureReadingServiceImpl
        implements TemperatureReadingService {

    private final TemperatureReadingRepository readingRepository;
    private final SensorService sensorService;
    private final TokenService tokenService;

    public TemperatureReadingServiceImpl(
            TemperatureReadingRepository readingRepository,
            SensorService sensorService,
            TokenService tokenService) {

        this.readingRepository = readingRepository;
        this.sensorService = sensorService;
        this.tokenService = tokenService;
    }

    @Override
    public TemperatureReading saveReading(String sensorIdentifier,
                                          Double readingValue) {

        SensorDevice sensor = sensorService.getByIdentifier(sensorIdentifier);

        // ✅ Sensor active check
        if (!Boolean.TRUE.equals(sensor.getIsActive())) {
            throw new IllegalArgumentException("Sensor not active");
        }

        // ✅ Reading validation
        if (readingValue == null || readingValue < -100 || readingValue > 200) {
            throw new IllegalArgumentException("Invalid reading");
        }

        ColdRoom coldRoom = sensor.getColdRoom();

        TemperatureReading reading = new TemperatureReading(
                sensor,
                coldRoom,
                readingValue,
                LocalDateTime.now()
        );

        readingRepository.save(reading);

        // ✅ BREACH CHECK (AUTO ALERT)
        if (readingValue < coldRoom.getMinAllowed()) {
            tokenService.createBreachAlert(
                    reading,
                    "LOW"
            );
        } else if (readingValue > coldRoom.getMaxAllowed()) {
            tokenService.createBreachAlert(
                    reading,
                    "HIGH"
            );
        }

        return reading;
    }
}
