package com.example.societyMaintenanceMgmt.dto;

import com.example.societyMaintenanceMgmt.entity.ResidentType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResidentResponseDto {

    private Long residentId;

    private Long flatId;

    private String residentName;

    private String mobileNumber;

    private String email;

    private ResidentType residentType;

    private Boolean primaryResident;

    private Boolean active;
}