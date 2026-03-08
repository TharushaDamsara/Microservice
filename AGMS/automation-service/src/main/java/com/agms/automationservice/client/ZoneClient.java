package com.agms.automationservice.client;

import com.agms.automationservice.model.ZoneThreshold;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "zone-service")
public interface ZoneClient {
    @GetMapping("/api/zones")
    List<ZoneThreshold> getAllZones();

    @GetMapping("/api/zones/{id}")
    ZoneThreshold getZoneById(@PathVariable("id") Long id);
}
