package com.example.societyMaintenanceMgmt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlatResponseDto {

    private Long flatId;

    private Long wingId;

    private String flatNumber;

    private Double areaSqFt;

    private Integer floorNumber;

    private Boolean active;
}