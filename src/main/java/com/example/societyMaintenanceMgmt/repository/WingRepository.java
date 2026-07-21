package com.example.societyMaintenanceMgmt.repository;

import com.example.societyMaintenanceMgmt.entity.Society;
import com.example.societyMaintenanceMgmt.entity.Wing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WingRepository extends JpaRepository<Wing, Long> {

    boolean existsBySocietyAndWingNameIgnoreCase(
            Society society,
            String wingName);

    List<Wing> findBySocietyAndActiveTrueOrderByWingNameAsc(
            Society society);

    Optional<Wing> findByWingIdAndSocietyAndActiveTrue(
            Long wingId,
            Society society);
}