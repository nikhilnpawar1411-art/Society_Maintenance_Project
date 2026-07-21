package com.example.societyMaintenanceMgmt.repository;

import com.example.societyMaintenanceMgmt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginId(String loginId);

    boolean existsByLoginId(String loginId);

    List<User> findBySocietyIdAndDeletedFalse(Long societyId);

    Optional<User> findByUserId(Long userId);

    List<User> findBySocietyIdAndIsActiveTrue(Long societyId);

    boolean existsByEmail(String email);
}