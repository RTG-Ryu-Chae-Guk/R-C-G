package com.rcg.RealEstateDeal.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RealEstateDeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sggNm;       // 시군구명 (예: 종로구)
    private String umdNm;       // 읍면동명
    private String buildingUse; // 건물 용도
    private int dealYear;
    private int dealMonth;
    private int dealDay;
    private String dealAmount;  // 거래금액 (문자열, 콤마 포함)
    private String buildingType;
    private String landUse;
    private String buyerGbn;
}