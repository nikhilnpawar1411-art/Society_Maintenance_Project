package com.example.societyMaintenanceMgmt.repository;

import com.example.societyMaintenanceMgmt.entity.Flat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface FlatRepository extends JpaRepository<Flat, Long> {

    boolean existsBySocietyIdAndFlatNumberAndActiveTrue(
            Long societyId,
            String flatNumber);

    Optional<Flat> findByFlatIdAndActiveTrue(Long flatId);

    List<Flat> findBySocietyIdAndActiveTrueOrderByFlatNumber(
            Long societyId);

    List<Flat> findBySocietyIdAndWingIdAndActiveTrueOrderByFlatNumber(
            Long societyId,
            Long wingId);

    List<Flat> findBySocietyIdAndActiveTrue(Long societyId);
}