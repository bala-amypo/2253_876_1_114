package com.example.demo.service;

import com.example.demo.entity.BreachAlert;
import com.example.demo.entity.TemperatureReading;

public interface TokenService {

    BreachAlert createBreachAlert(TemperatureReading reading,
                                  String breachType);

    BreachAlert updateStatus(Long tokenId, String newStatus);

    BreachAlert getToken(Long tokenId);
}
