package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.demo.entity.TemperatureReading;
import com.example.demo.service.TemperatureReadingService;

@RestController
@RequestMapping("/api/readings")
public class TemperatureReadingController {

    private final TemperatureReadingService tempService;

    public TemperatureReadingController(TemperatureReadingService tempService) {
        this.tempService = tempService;
    }

    // Save Reading
    @PostMapping("/{deviceId}")
    public TemperatureReading saveReading(
            @PathVariable String deviceId,
            @RequestParam Double value) {
        return tempService.saveReading(deviceId, value);
    }

    // Get All Readings
    @GetMapping
    public List<TemperatureReading> getAll() {
        return tempService.getAllReadings();
    }

    // Get Single Reading
    @GetMapping("/{id}")
    public TemperatureReading getReading(@PathVariable Long id) {
        return tempService.getReading(id);
    }
}
