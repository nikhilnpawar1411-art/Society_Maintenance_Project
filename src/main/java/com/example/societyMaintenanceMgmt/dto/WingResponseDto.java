package com.example.societyMaintenanceMgmt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WingResponseDto {

    private Long wingId;
    private String wingName;
    private Boolean active;
}