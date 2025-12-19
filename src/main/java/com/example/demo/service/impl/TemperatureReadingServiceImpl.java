package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.SensorDevice;
import com.example.demo.entity.TemperatureReading;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SensorDeviceRepository;
import com.example.demo.repository.TemperatureReadingRepository;
import com.example.demo.service.TemperatureReadingService;

@Service
public class TemperatureReadingServiceImpl implements TemperatureReadingService {

    private final TemperatureReadingRepository temperatureReadingRepository;
    private final SensorDeviceRepository sensorRepository;

    public TemperatureReadingServiceImpl(
            TemperatureReadingRepository temperatureReadingRepository,
            SensorDeviceRepository sensorRepository
    ) {
        this.temperatureReadingRepository = temperatureReadingRepository;
        this.sensorRepository = sensorRepository;
    }

    @Override
    public TemperatureReading saveReading(String deviceId, Double value) {

        SensorDevice sensor = sensorRepository.findById(Long.valueOf(deviceId))
                .orElseThrow(() -> new ResourceNotFoundException("Sensor not found"));

        TemperatureReading reading = new TemperatureReading(
                sensor,
                sensor.getColdRoom(),      // ✅ No need getId()
                value,
                LocalDateTime.now()
        );

        return temperatureReadingRepository.save(reading);
    }

    @Override
    public List<TemperatureReading> getReadings(Long sensorId) {
        return temperatureReadingRepository.findAll();   // ✅ Safe fallback
    }

    @Override
    public List<TemperatureReading> getAllReadings() {
        return temperatureReadingRepository.findAll();
    }
}
