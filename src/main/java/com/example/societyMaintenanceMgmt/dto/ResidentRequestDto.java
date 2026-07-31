package com.example.societyMaintenanceMgmt.dto;

import com.example.societyMaintenanceMgmt.entity.ResidentType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResidentRequestDto {

    @NotNull
    private Long flatId;

    @NotBlank
    private String residentName;

    @NotBlank
    private String mobileNumber;

    @Email
    private String email;

    @NotNull
    private ResidentType residentType;

    private Boolean primaryResident = false;
}