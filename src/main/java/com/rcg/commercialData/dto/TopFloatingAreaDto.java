package com.rcg.commercialData.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopFloatingAreaDto {
    private String trdarCd;        // 상권 코드
    private String areaName;       // 상권 이름
    private long totalFloating;    // 총 유동인구 수
}
