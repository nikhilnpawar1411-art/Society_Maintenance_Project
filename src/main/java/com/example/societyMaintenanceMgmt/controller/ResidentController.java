package com.example.societyMaintenanceMgmt.controller;

import com.example.societyMaintenanceMgmt.dto.ResidentRequestDto;
import com.example.societyMaintenanceMgmt.dto.ResidentResponseDto;
import com.example.societyMaintenanceMgmt.service.IResidentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resident")
@RequiredArgsConstructor
public class ResidentController {

    private final IResidentService residentService;

    @PostMapping
    public ResponseEntity<ResidentResponseDto> createResident(
            @Valid @RequestBody ResidentRequestDto request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(residentService.createResident(request));
    }

    @GetMapping
    public ResponseEntity<List<ResidentResponseDto>> getAllResidents() {

        return ResponseEntity.ok(residentService.getAllResidents());
    }

    @GetMapping("/{residentId}")
    public ResponseEntity<ResidentResponseDto> getResident(
            @PathVariable Long residentId) {

        return ResponseEntity.ok(residentService.getResident(residentId));
    }

    @PutMapping("/{residentId}")
    public ResponseEntity<ResidentResponseDto> updateResident(
            @PathVariable Long residentId,
            @Valid @RequestBody ResidentRequestDto request) {

        return ResponseEntity.ok(
                residentService.updateResident(residentId, request));
    }

    @DeleteMapping("/{residentId}")
    public ResponseEntity<String> deleteResident(
            @PathVariable Long residentId) {

        residentService.deleteResident(residentId);

        return ResponseEntity.ok("Resident deleted successfully.");
    }
}