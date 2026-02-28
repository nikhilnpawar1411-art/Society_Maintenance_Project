package com.example.societyMaintenanceMgmt.repository;

import com.example.societyMaintenanceMgmt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginId(String loginId);
    boolean existsByLoginId(String loginId);
    List<User> findBySocietyId(Long societyId);
}