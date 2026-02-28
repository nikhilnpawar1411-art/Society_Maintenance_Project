package com.example.societyMaintenanceMgmt.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;

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
}
