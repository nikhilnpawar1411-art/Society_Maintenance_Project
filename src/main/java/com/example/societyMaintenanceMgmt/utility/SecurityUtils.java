package com.example.societyMaintenanceMgmt.utility;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    private SecurityUtils() {
    }

    public static LoggedInUser getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || authentication.getPrincipal() == null
                || "anonymousUser".equals(authentication.getPrincipal())) {
            return null;
        }

        return (LoggedInUser) authentication.getPrincipal();
    }
}
