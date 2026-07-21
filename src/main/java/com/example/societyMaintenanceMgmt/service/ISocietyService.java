package com.example.societyMaintenanceMgmt.service;

import com.example.societyMaintenanceMgmt.dto.SocietyResponseDto;
import com.example.societyMaintenanceMgmt.entity.Society;
import org.jspecify.annotations.Nullable;

public interface ISocietyService {
    @Nullable Society updateSociety(Society society);
    SocietyResponseDto getSociety();
}
