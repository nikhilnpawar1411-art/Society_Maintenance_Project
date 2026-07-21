package com.example.societyMaintenanceMgmt.service;

import com.example.societyMaintenanceMgmt.dto.LoginRequestDto;
import com.example.societyMaintenanceMgmt.dto.LoginResponseDto;
import com.example.societyMaintenanceMgmt.dto.SocietyRegistrationRequestDto;
import org.jspecify.annotations.Nullable;

public interface IAuthService {
    LoginResponseDto login(LoginRequestDto request);

    @Nullable LoginResponseDto register(SocietyRegistrationRequestDto request);
}
