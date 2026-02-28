package com.example.societyMaintenanceMgmt.service;

import com.example.societyMaintenanceMgmt.entity.Society;
import org.jspecify.annotations.Nullable;

public interface iMasterService {
    @Nullable Society updateSociety(Society society);
}
