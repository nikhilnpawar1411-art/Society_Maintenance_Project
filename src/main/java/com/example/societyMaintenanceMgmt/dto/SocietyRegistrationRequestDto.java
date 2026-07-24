package com.example.societyMaintenanceMgmt.dto;

import lombok.Data;

@Data
public class SocietyRegistrationRequestDto {
    //Society Details
    private String societyName;
    private String societyAddress;
    private String societyRegistrationNumber;
    private Boolean hasWing;
    private String city;
    private String state;
    private String country;
    private String pincode;
    //Admin Details
    private String loginId;
    private String userName;
    private String email;
    private String password;
}
