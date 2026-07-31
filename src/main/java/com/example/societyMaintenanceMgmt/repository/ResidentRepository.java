package com.example.societyMaintenanceMgmt.repository;

import com.example.societyMaintenanceMgmt.entity.Resident;
import com.example.societyMaintenanceMgmt.entity.ResidentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, Long> {

    List<Resident> findBySocietyIdAndActiveTrue(Long societyId);

    List<Resident> findByFlatIdAndActiveTrue(Long flatId);

    Optional<Resident> findByResidentIdAndActiveTrue(Long residentId);

    boolean existsByFlatIdAndResidentTypeAndPrimaryResidentTrueAndActiveTrue(
            Long flatId,
            ResidentType residentType);
}