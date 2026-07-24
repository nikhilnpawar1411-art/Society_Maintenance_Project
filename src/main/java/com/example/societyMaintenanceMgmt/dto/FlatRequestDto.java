package com.example.societyMaintenanceMgmt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FlatRequestDto {

    private Long wingId;

    @NotBlank(message = "Flat Number is required")
    private String flatNumber;

    @NotNull(message = "Area is required")
    private Double areaSqFt;

    private Integer floorNumber;
}