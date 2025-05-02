package com.rcg.community.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class BusinessUser {
    @Id
    @Column(name = "business_number")
    private String b_no;
    private String taxType;
    private String status;
    private LocalDateTime verifiedAt;
}