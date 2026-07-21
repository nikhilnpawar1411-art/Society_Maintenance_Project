package com.example.societyMaintenanceMgmt.controller;

import com.example.societyMaintenanceMgmt.dto.SocietyResponseDto;
import com.example.societyMaintenanceMgmt.entity.Society;
import com.example.societyMaintenanceMgmt.service.ISocietyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/master")
@RequiredArgsConstructor
public class SocietyController {
    private final ISocietyService societyService;

    @PutMapping("/updateSociety")
    public ResponseEntity<Society> update(@RequestBody Society society) {
        return ResponseEntity.status(HttpStatus.OK).body(societyService.updateSociety(society));
    }

    @GetMapping("/me")
    public ResponseEntity<SocietyResponseDto> getSociety() {
        return ResponseEntity.ok(societyService.getSociety());
    }

}
