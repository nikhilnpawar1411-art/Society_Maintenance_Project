package com.example.societyMaintenanceMgmt.controller;

import com.example.societyMaintenanceMgmt.dto.LoginRequestDto;
import com.example.societyMaintenanceMgmt.dto.LoginResponseDto;
import com.example.societyMaintenanceMgmt.dto.SocietyRegistrationRequestDto;
import com.example.societyMaintenanceMgmt.serviceImpl.AuthServiceImpl;
import com.example.societyMaintenanceMgmt.utility.LoggedInUser;
import com.example.societyMaintenanceMgmt.utility.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authService;

    @   PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(request));

    }

    @PostMapping("/register-society")
    public ResponseEntity<LoginResponseDto> register(
            @RequestBody SocietyRegistrationRequestDto request) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.register(request));
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {

        LoggedInUser user = SecurityUtils.getCurrentUser();

        return ResponseEntity.ok(
                "UserId=" + user.getUserId()
                        + ", SocietyId=" + user.getSocietyId()
                        + ", LoginId=" + user.getLoginId()
                        + ", userName=" + user.getUserName()
                        + ", Role=" + user.getRole()
        );
    }

}
