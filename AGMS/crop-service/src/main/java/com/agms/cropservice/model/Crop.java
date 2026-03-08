package com.agms.cropservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "crops")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Crop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String variety;
    
    @Enumerated(EnumType.STRING)
    private CropStatus status;
}

enum CropStatus {
    SEEDLING, VEGETATIVE, HARVESTED
}
