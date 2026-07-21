package com.example.societyMaintenanceMgmt.service;

import com.example.societyMaintenanceMgmt.dto.WingRequestDto;
import com.example.societyMaintenanceMgmt.dto.WingResponseDto;

import java.util.List;

public interface IWingService {

    WingResponseDto createWing(WingRequestDto request);

    List<WingResponseDto> getAllWings();

    WingResponseDto updateWing(WingRequestDto request);

    void deleteWing(Long wingId);
}