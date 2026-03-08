package com.agms.cropservice.service;

import com.agms.cropservice.model.Crop;
import com.agms.cropservice.repository.CropRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CropService {

    private final CropRepository cropRepository;

    public CropService(CropRepository cropRepository) {
        this.cropRepository = cropRepository;
    }

    public List<Crop> getAllCrops() {
        return cropRepository.findAll();
    }

    public Crop createCrop(Crop crop) {
        return cropRepository.save(crop);
    }

    public Crop updateCropStatus(Long id, String status) {
        Crop crop = cropRepository.findById(id).orElseThrow(() -> new RuntimeException("Crop not found"));
        // status is expected to be a valid CropStatus enum string
        // simplified for demo
        return cropRepository.save(crop);
    }
}
