package com.example.societyMaintenanceMgmt.controller;

import com.example.societyMaintenanceMgmt.dto.FlatRequestDto;
import com.example.societyMaintenanceMgmt.dto.FlatResponseDto;
import com.example.societyMaintenanceMgmt.service.IFlatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flat")
@RequiredArgsConstructor
public class FlatController {

    @Autowired
    private final IFlatService flatService;

    @PostMapping
    public ResponseEntity<FlatResponseDto> createFlat(
            @Valid @RequestBody FlatRequestDto request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(flatService.createFlat(request));
    }

    @GetMapping
    public ResponseEntity<List<FlatResponseDto>> getAllFlats() {

        return ResponseEntity.ok(flatService.getAllFlats());
    }

    @GetMapping("/{flatId}")
    public ResponseEntity<FlatResponseDto> getFlat(
            @PathVariable Long flatId) {

        return ResponseEntity.ok(flatService.getFlat(flatId));
    }

    @PutMapping("/{flatId}")
    public ResponseEntity<FlatResponseDto> updateFlat(
            @PathVariable Long flatId,
            @Valid @RequestBody FlatRequestDto request) {

        return ResponseEntity.ok(
                flatService.updateFlat(flatId, request));
    }

    @DeleteMapping("/{flatId}")
    public ResponseEntity<String> deleteFlat(
            @PathVariable Long flatId) {

        flatService.deleteFlat(flatId);

        return ResponseEntity.ok("Flat deleted successfully.");
    }
}