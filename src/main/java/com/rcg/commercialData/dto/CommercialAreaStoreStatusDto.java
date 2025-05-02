package com.rcg.commercialData.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommercialAreaStoreStatusDto {
    private String trdarCd;
    private String trdarCdNm;
    private String stdrYyquCd;
    private String svcIndutyCd;
    private String svcIndutyCdNm;
    private Integer storeCo;
    private Integer similrStoreCo;
    private Double openRt;
    private Integer openStoreCo;
    private Double closeRt;
    private Integer closeStoreCo;
    private Integer frnchsStoreCo;
}

