package com.example.societyMaintenanceMgmt.repository;

import com.example.societyMaintenanceMgmt.entity.Society;
import com.example.societyMaintenanceMgmt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocietyRepository extends JpaRepository<Society, Long> {
    Optional<Society> findBySocietyId(Long id);
}