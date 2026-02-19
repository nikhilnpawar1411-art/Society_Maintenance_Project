package com.example.societyMaintenanceMgmt.utility;


public class SocietyContext {

    private static final ThreadLocal<Long> SOCIETY = new ThreadLocal<>();

    public static void setSocietyId(Long societyId) {
        SOCIETY.set(societyId);
    }

    public static Long getsocietyId() {
        return SOCIETY.get();
    }

    public static void clear() {
        SOCIETY.remove();
    }
}
