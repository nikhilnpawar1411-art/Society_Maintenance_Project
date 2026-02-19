package com.example.societyMaintenanceMgmt.service;

import com.example.societyMaintenanceMgmt.dto.LoginRequestDto;
import com.example.societyMaintenanceMgmt.dto.LoginResponseDto;

public interface iAuthService {
    LoginResponseDto login(LoginRequestDto request);
}
