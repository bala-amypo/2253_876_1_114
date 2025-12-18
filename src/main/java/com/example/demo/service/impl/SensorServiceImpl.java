package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.SensorDevice;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SensorDeviceRepository;
import com.example.demo.service.SensorService;

@Service
public class SensorServiceImpl implements SensorService {

    private final SensorDeviceRepository sensorRepository;

    // ✅ Constructor injection
    public SensorServiceImpl(SensorDeviceRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public SensorDevice registerSensor(SensorDevice sensor) {

        // ✅ Unique identifier check
        if (sensorRepository.findByIdentifier(sensor.getIdentifier()).isPresent()) {
            throw new IllegalArgumentException("Sensor identifier already exists");
        }

        return sensorRepository.save(sensor);
    }

    @Override
    public SensorDevice updateSensorStatus(Long sensorId, Boolean isActive) {

        SensorDevice sensor = sensorRepository.findById(sensorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Sensor not found"));

        sensor.setIsActive(isActive);
        return sensorRepository.save(sensor);
    }

    @Override
    public List<SensorDevice> getAllSensors() {
        return sensorRepository.findAll();
    }

    @Override
    public SensorDevice getByIdentifier(String identifier) {
        return sensorRepository.findByIdentifier(identifier)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Sensor not found"));
    }
}
