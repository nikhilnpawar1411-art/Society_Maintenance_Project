package com.example.societyMaintenanceMgmt.controller;

import com.example.societyMaintenanceMgmt.entity.Society;
import com.example.societyMaintenanceMgmt.service.iMasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/master")
@RequiredArgsConstructor
public class MasterController {
    private final iMasterService iMasterService;

    @PutMapping("/updateSociety")
    public ResponseEntity<Society> update(@RequestBody Society society) {
        return ResponseEntity.status(HttpStatus.OK).body(iMasterService.updateSociety(society));
    }



}
