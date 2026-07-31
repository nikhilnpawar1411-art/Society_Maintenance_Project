package com.example.societyMaintenanceMgmt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "flat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flat  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flatId;

    @Column(nullable = false)
    private Long societyId;

    private Long wingId;

    @Column(nullable = false, length = 20)
    private String flatNumber;

    @Column(nullable = false)
    private Double areaSqFt;

    private Integer floorNumber;

    @Column(nullable = false)
    private Boolean active = true;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
