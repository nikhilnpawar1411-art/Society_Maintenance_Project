package com.example.societyMaintenanceMgmt.service;

import com.example.societyMaintenanceMgmt.dto.FlatRequestDto;
import com.example.societyMaintenanceMgmt.dto.FlatResponseDto;

import java.util.List;

public interface IFlatService {

    FlatResponseDto createFlat(FlatRequestDto request);

    List<FlatResponseDto> getAllFlats();

    FlatResponseDto getFlat(Long flatId);

    FlatResponseDto updateFlat(Long flatId,
                               FlatRequestDto request);

    void deleteFlat(Long flatId);
}