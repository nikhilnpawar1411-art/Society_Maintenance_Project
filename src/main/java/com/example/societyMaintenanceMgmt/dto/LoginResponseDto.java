package com.example.societyMaintenanceMgmt.dto;

import com.example.societyMaintenanceMgmt.entity.Society;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private String token;
    private Long userId;
    private String role;
    private String userName;
    private Society society;

}
