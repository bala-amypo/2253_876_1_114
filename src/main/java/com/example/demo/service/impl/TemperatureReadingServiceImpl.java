package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.TemperatureReading;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.TemperatureReadingRepository;
import com.example.demo.service.TemperatureReadingService;

@Service
public class TemperatureReadingServiceImpl implements TemperatureReadingService {

    private final TemperatureReadingRepository temperatureRepository;

    public TemperatureReadingServiceImpl(TemperatureReadingRepository temperatureRepository) {
        this.temperatureRepository = temperatureRepository;
    }

    @Override
    public TemperatureReading saveReading(TemperatureReading reading) {

        // Set created time if needed
        reading.setTimestamp(LocalDateTime.now());

        return temperatureRepository.save(reading);
    }

    @Override
    public TemperatureReading getReading(Long id) {
        return temperatureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Temperature Reading not found"));
    }

    @Override
    public List<TemperatureReading> getAllReadings() {
        return temperatureRepository.findAll();
    }
}
