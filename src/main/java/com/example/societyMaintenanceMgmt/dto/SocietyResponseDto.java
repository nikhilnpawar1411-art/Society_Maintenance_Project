package com.example.societyMaintenanceMgmt.dto;

import lombok.Data;

@Data
public class SocietyResponseDto {

//    private Long societyId;
    private String societyName;
    private String societyAddress;
    private String societyRegistrationNumber;
    private Boolean active;
}