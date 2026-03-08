package com.agms.sensorservice.service;

import com.agms.sensorservice.model.Telemetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class SensorService {

    private final WebClient webClient;
    private final AtomicReference<Telemetry> latestTelemetry = new AtomicReference<>();

    @Value("${iot-api.base-url}")
    private String iotApiUrl;

    public SensorService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Scheduled(fixedRateString = "${telemetry.interval:10000}")
    public void fetchTelemetry() {
        // In a real scenario, we'd iterate over all registered devices
        // For demo, we use a placeholder device ID
        String deviceId = "DEV-123"; 

        webClient.get()
                .uri(iotApiUrl + "/devices/telemetry/" + deviceId)
                .retrieve()
                .bodyToMono(Telemetry.class)
                .doOnNext(telemetry -> {
                    telemetry.setDeviceId(deviceId);
                    latestTelemetry.set(telemetry);
                    forwardToAutomation(telemetry);
                })
                .subscribe();
    }

    private void forwardToAutomation(Telemetry telemetry) {
        webClient.post()
                .uri("http://automation-service/api/automation/process")
                .bodyValue(telemetry)
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe();
    }

    public Telemetry getLatestTelemetry() {
        return latestTelemetry.get();
    }
}
