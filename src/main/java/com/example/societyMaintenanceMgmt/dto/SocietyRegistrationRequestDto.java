package com.example.societyMaintenanceMgmt.dto;

import lombok.Data;

@Data
public class SocietyRegistrationRequestDto {
    //Society Details
    private String societyName;
    private String societyAddress;
    private String societyRegistrationNumber;
    //Admin Details
    private String loginId;
    private String userName;
    private String email;
    private String password;
}
