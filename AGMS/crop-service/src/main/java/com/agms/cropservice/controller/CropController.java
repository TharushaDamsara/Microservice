package com.agms.cropservice.controller;

import com.agms.cropservice.model.Crop;
import com.agms.cropservice.service.CropService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/crops")
public class CropController {

    private final CropService cropService;

    public CropController(CropService cropService) {
        this.cropService = cropService;
    }

    @GetMapping
    public List<Crop> getAllCrops() {
        return cropService.getAllCrops();
    }

    @PostMapping
    public Crop createCrop(@RequestBody Crop crop) {
        return cropService.createCrop(crop);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Crop> updateStatus(@PathVariable Long id, @RequestBody Map<String, String> statusMap) {
        return ResponseEntity.ok(cropService.updateCropStatus(id, statusMap.get("status")));
    }
}
