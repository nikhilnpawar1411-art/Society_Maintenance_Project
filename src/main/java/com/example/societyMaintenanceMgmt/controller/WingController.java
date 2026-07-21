package com.example.societyMaintenanceMgmt.controller;

import com.example.societyMaintenanceMgmt.dto.WingRequestDto;
import com.example.societyMaintenanceMgmt.dto.WingResponseDto;
import com.example.societyMaintenanceMgmt.service.IWingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wing")
@RequiredArgsConstructor
public class WingController {

    @Autowired
    IWingService wingService;

    @PostMapping
    public ResponseEntity<WingResponseDto> createWing(
            @Valid @RequestBody WingRequestDto request) {

        return ResponseEntity
                    .status(HttpStatus.CREATED)
                .body(wingService.createWing(request));
    }

    @GetMapping
    public ResponseEntity<List<WingResponseDto>> getAllWings() {

        return ResponseEntity.ok(wingService.getAllWings());
    }

    @PutMapping("/{wingId}")
    public ResponseEntity<WingResponseDto> updateWing(
            @Valid @RequestBody WingRequestDto request) {

        return ResponseEntity.ok(
                wingService.updateWing(request));
    }

    @DeleteMapping("/{wingId}")
    public ResponseEntity<String> deleteWing(
            @PathVariable Long wingId) {

        wingService.deleteWing(wingId);

        return ResponseEntity.ok("Wing deleted successfully.");
    }
}