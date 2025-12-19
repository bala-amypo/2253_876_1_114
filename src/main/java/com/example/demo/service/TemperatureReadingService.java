package com.example.demo.service;

import com.example.demo.entity.TemperatureReading;

public interface TemperatureReadingService {

    TemperatureReading saveReading(String sensorIdentifier,
                                   Double readingValue);
}
