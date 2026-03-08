package com.agms.automationservice.service;

import com.agms.automationservice.client.ZoneClient;
import com.agms.automationservice.model.ZoneThreshold;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class AutomationService {

    private final ZoneClient zoneClient;
    private final List<String> actionLogs = new ArrayList<>();

    public AutomationService(ZoneClient zoneClient) {
        this.zoneClient = zoneClient;
    }

    public void processTelemetry(Map<String, Object> telemetry) {
        String deviceId = (String) telemetry.get("deviceId");
        Double currentTemp = (Double) telemetry.get("temperature");

        log.info("Processing telemetry for device: {} | Temp: {}", deviceId, currentTemp);

        List<ZoneThreshold> zones = zoneClient.getAllZones();
        zones.stream()
                .filter(z -> deviceId.equals(z.getDeviceId()))
                .forEach(z -> {
                    if (currentTemp > z.getMaxTemp()) {
                        String action = "TURN_FAN_ON for Zone: " + z.getName();
                        log.warn(action);
                        actionLogs.add(action);
                    } else if (currentTemp < z.getMinTemp()) {
                        String action = "TURN_HEATER_ON for Zone: " + z.getName();
                        log.warn(action);
                        actionLogs.add(action);
                    }
                });
    }

    public List<String> getActionLogs() {
        return actionLogs;
    }
}
