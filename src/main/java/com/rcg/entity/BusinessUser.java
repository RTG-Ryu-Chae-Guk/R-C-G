package com.rcg.entity;

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
    private String b_no; // 사업자등록번호 (PK)

    private String taxType;
    private String status;
    private LocalDateTime verifiedAt;
}