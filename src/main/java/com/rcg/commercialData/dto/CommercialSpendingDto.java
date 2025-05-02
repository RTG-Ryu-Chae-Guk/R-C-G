package com.rcg.commercialData.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommercialSpendingDto {
    private String trdarCd;             // 상권 코드
    private String trdarCdNm;           // 상권 코드명
    private String stdrYyquCd;          // 기준_년분기_코드

    private Long totalExpendAmt;        // 총 지출 금액
    private Long foodExpendAmt;         // 식료품 지출
    private Long clothingExpendAmt;     // 의류/신발 지출
    private Long livingExpendAmt;       // 생활용품 지출
    private Long medicalExpendAmt;      // 의료비 지출
    private Long transportExpendAmt;    // 교통 지출
    private Long leisureExpendAmt;      // 여가 지출
    private Long cultureExpendAmt;      // 문화 지출
    private Long educationExpendAmt;    // 교육 지출
    private Long entertainmentExpendAmt;// 유흥 지출
}
