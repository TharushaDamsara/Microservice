package com.agms.automationservice.controller;

import com.agms.automationservice.service.AutomationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/automation")
public class AutomationController {

    private final AutomationService automationService;

    public AutomationController(AutomationService automationService) {
        this.automationService = automationService;
    }

    @PostMapping("/process")
    public void processTelemetry(@RequestBody Map<String, Object> telemetry) {
        automationService.processTelemetry(telemetry);
    }

    @GetMapping("/logs")
    public List<String> getActionLogs() {
        return automationService.getActionLogs();
    }
}
