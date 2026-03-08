package com.agms.sensorservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Telemetry {
    private String deviceId;
    private Double temperature;
    private Double humidity;
    private Long timestamp;
}
