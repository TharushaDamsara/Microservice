package com.agms.automationservice.model;

import lombok.Data;

@Data
public class ZoneThreshold {
    private Long id;
    private String name;
    private Double minTemp;
    private Double maxTemp;
    private String deviceId;
}
