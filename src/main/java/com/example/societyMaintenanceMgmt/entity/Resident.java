package com.example.societyMaintenanceMgmt.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Table(name = "resident")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long residentId;

    @Column(nullable = false)
    private Long societyId;

    @Column(nullable = false)
    private Long flatId;

    @Column(nullable = false, length = 100)
    private String residentName;

    @Column(nullable = false, length = 15)
    private String mobileNumber;

    private String email;

    @Enumerated(EnumType.STRING)
    private ResidentType residentType;

//    @Column(nullable = false)
//    private String residentType; // OWNER / TENANT

    @Column(nullable = false)
    private Boolean primaryResident = false;

    @Column(nullable = false)
    private Boolean active = true;
}