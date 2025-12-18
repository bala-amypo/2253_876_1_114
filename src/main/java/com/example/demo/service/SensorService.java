package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.SensorDevice;

public interface SensorService {

    SensorDevice registerSensor(SensorDevice sensor);

    SensorDevice updateSensorStatus(Long sensorId, Boolean isActive);

    List<SensorDevice> getAllSensors();

    SensorDevice getByIdentifier(String identifier);
}
