package com.example.societyMaintenanceMgmt.service;

import com.example.societyMaintenanceMgmt.dto.UserRequestDto;
import com.example.societyMaintenanceMgmt.dto.UserResponseDto;

import java.util.List;

public interface IUserService {
        UserResponseDto createUser(UserRequestDto request);

        List<UserResponseDto> getUsers();

        UserResponseDto getUser(Long userId);

        UserResponseDto updateUser(Long userId,
                                   UserRequestDto request);

        void deleteUser(Long userId);
}


