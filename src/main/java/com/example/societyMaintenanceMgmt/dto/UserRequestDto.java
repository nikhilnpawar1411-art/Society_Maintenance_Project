package com.example.societyMaintenanceMgmt.dto;

import lombok.Data;

@Data
public class UserRequestDto {

    private String loginId;
    private String userName;
    private String email;
    private String password;
    private String role;
}