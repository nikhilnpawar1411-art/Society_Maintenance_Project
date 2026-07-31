package com.example.societyMaintenanceMgmt.service;

import com.example.societyMaintenanceMgmt.dto.ResidentRequestDto;
import com.example.societyMaintenanceMgmt.dto.ResidentResponseDto;

import java.util.List;

public interface IResidentService {

    ResidentResponseDto createResident(ResidentRequestDto request);

    List<ResidentResponseDto> getAllResidents();

    ResidentResponseDto getResident(Long residentId);

    ResidentResponseDto updateResident(Long residentId,
                                       ResidentRequestDto request);

    void deleteResident(Long residentId);
}