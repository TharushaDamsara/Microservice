package com.agms.zoneservice.service;

import com.agms.zoneservice.model.Zone;
import com.agms.zoneservice.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class ZoneService {

    private final ZoneRepository zoneRepository;
    private final WebClient webClient;

    @Value("${iot-api.base-url}")
    private String iotApiUrl;

    public ZoneService(ZoneRepository zoneRepository, WebClient.Builder webClientBuilder) {
        this.zoneRepository = zoneRepository;
        this.webClient = webClientBuilder.build();
    }

    public List<Zone> getAllZones() {
        return zoneRepository.findAll();
    }

    public Zone getZoneById(Long id) {
        return zoneRepository.findById(id).orElseThrow(() -> new RuntimeException("Zone not found"));
    }

    public Zone createZone(Zone zone) {
        if (zone.getMinTemp() >= zone.getMaxTemp()) {
            throw new RuntimeException("minTemp must be less than maxTemp");
        }

        // Register device with External IoT API
        String deviceId = registerDeviceWithIotApi(zone.getName()).block();
        zone.setDeviceId(deviceId);

        return zoneRepository.save(zone);
    }

    public Zone updateZone(Long id, Zone zoneDetails) {
        Zone zone = getZoneById(id);
        zone.setName(zoneDetails.getName());
        zone.setMinTemp(zoneDetails.getMinTemp());
        zone.setMaxTemp(zoneDetails.getMaxTemp());
        return zoneRepository.save(zone);
    }

    public void deleteZone(Long id) {
        Zone zone = getZoneById(id);
        zoneRepository.delete(zone);
    }

    private Mono<String> registerDeviceWithIotApi(String name) {
        // Mocking the IoT API call for registration
        // In a real scenario, we'd first login to get a token, then POST /devices
        return webClient.post()
                .uri(iotApiUrl + "/devices")
                .bodyValue(Map.of("name", name, "type", "GREENHOUSE_NODE"))
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> (String) response.get("id"))
                .onErrorReturn("DEV-" + System.currentTimeMillis()); // Fallback for demo
    }
}
