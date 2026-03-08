package com.agms.sensorservice.controller;

import com.agms.sensorservice.model.Telemetry;
import com.agms.sensorservice.service.SensorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {

    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping("/latest")
    public Telemetry getLatestTelemetry() {
        return sensorService.getLatestTelemetry();
    }
}
