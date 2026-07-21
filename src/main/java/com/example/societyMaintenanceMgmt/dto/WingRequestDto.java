package com.example.societyMaintenanceMgmt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class WingRequestDto {

    @NotBlank(message = "Wing name is required")
    @Size(max = 20, message = "Wing name cannot exceed 20 characters")
    private String wingName;
    private Long wingId;
}