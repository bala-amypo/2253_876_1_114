package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.ColdRoom;
import com.example.demo.entity.SensorDevice;
import com.example.demo.entity.TemperatureReading;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ColdRoomRepository;
import com.example.demo.repository.SensorDeviceRepository;
import com.example.demo.repository.TemperatureReadingRepository;
import com.example.demo.service.TemperatureReadingService;

@Service
public class TemperatureReadingServiceImpl implements TemperatureReadingService {

    private final TemperatureReadingRepository temperatureReadingRepository;
    private final SensorDeviceRepository sensorRepository;
    private final ColdRoomRepository coldRoomRepository;

    public TemperatureReadingServiceImpl(
            TemperatureReadingRepository temperatureReadingRepository,
            SensorDeviceRepository sensorRepository,
            ColdRoomRepository coldRoomRepository) {

        this.temperatureReadingRepository = temperatureReadingRepository;
        this.sensorRepository = sensorRepository;
        this.coldRoomRepository = coldRoomRepository;
    }

    @Override
    public TemperatureReading saveReading(String deviceId, Double value) {

        SensorDevice sensor = sensorRepository.findById(Long.valueOf(deviceId))
                .orElseThrow(() -> new ResourceNotFoundException("Sensor not found"));

        ColdRoom coldRoom = coldRoomRepository.findById(
                        sensor.getColdRoom().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cold room not found"));

        TemperatureReading reading = new TemperatureReading(
                sensor,
                coldRoom,
                value,
                LocalDateTime.now()
        );

        return temperatureReadingRepository.save(reading);
    }

    @Override
    public List<TemperatureReading> getReadings(Long sensorId) {
        return temperatureReadingRepository.findBySensorId(sensorId);
    }
}
