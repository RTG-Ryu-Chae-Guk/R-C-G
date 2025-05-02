package com.rcg.commercialData.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "commercial_area", indexes = {
        @Index(name = "idx_adstrd_cd", columnList = "adstrdCd")
})
public class CommercialArea {

    @Id
    @Column(name = "trdar_cd")
    private String trdarCd; // 상권 코드 (유니크한 상권 식별자)

    private String trdarCdNm; // 상권 코드 명 (상권 이름)

    private String trdarSeCd; // 상권 구분 코드 (A: 골목상권, B: 전통시장 등)

    private String trdarSeCdNm; // 상권 구분 코드 명

    private String adstrdCd; // 행정동 코드

    private String adstrdCdNm; // 행정동 이름

    private String signguCd; // 자치구 코드 (행정구역 코드)

    private String signguCdNm; // 자치구 이름

    private Double relmAr; // 상권 영역 면적 (m²)

    private Double xcntsValue; // TM기준 X좌표

    private Double ydntsValue; // TM기준 Y좌표

    private Double latitude;   // 위도 (WGS84)
    private Double longitude;  // 경도 (WGS84)
}
