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

    private final TemperatureReadingRepository temperatureRepository;
    private final SensorDeviceRepository sensorRepository;
 


    private final ColdRoomRepository coldRoomRepository;

    // public TemperatureReadingServiceImpl(
    //         TemperatureReadingRepository temperatureRepository,
    //         SensorRepository sensorRepository,
    //         ColdRoomRepository coldRoomRepository) {
    //     this.temperatureRepository = temperatureRepository;
    //     this.sensorRepository = sensorRepository;
    //     this.coldRoomRepository = coldRoomRepository;
    // }
    public TemperatureReadingServiceImpl(
        SensorDeviceRepository sensorRepository,
        ColdRoomRepository coldRoomRepository,
        TemperatureReadingRepository temperatureReadingRepository
) {
    this.sensorRepository = sensorRepository;
    this.coldRoomRepository = coldRoomRepository;
    this.temperatureReadingRepository = temperatureReadingRepository;
}


    @Override
    public TemperatureReading saveReading(String sensorId, Double value) {

       SensorDevice sensor = sensorRepository.findById(Long.valueOf(deviceId))
        .orElseThrow(() -> new ResourceNotFoundException("Sensor not found"));


        ColdRoom coldRoom = sensor.getColdRoom();

        TemperatureReading reading = new TemperatureReading();
        reading.setSensor(sensor);
        reading.setColdRoom(coldRoom);
        reading.setReadingValue(value);
        reading.setRecordedAt(LocalDateTime.now());

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
