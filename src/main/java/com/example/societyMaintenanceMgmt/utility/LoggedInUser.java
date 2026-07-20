package com.example.societyMaintenanceMgmt.utility;

import lombok.*;

@Getter
@AllArgsConstructor
public class LoggedInUser {

    private Long userId;
    private Long societyId;
    private String loginId;
    private String role;

}
