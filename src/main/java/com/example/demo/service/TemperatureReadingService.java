package com.example.demo.service;

import com.example.demo.entity.TemperatureReading;
import java.util.List;
public interface TemperatureReadingService {

    TemperatureReading saveReading(String deviceId, Double value);

    TemperatureReading getReading(Long id);

    List<TemperatureReading> getAllReadings();
}
