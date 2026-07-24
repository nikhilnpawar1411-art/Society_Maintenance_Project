package com.example.societyMaintenanceMgmt.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="society")
@Data
public class Society {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long societyId;

    private String societyName;
    private String societyAddress;
    private String societyRegistrationNumber;
    private boolean isActive = true;
    private Boolean hasWing=false;
    private Boolean setupCompleted=false;
    private String city;
    private String state;
    private String country;
    private String pincode;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
