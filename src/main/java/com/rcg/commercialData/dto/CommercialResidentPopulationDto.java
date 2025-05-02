package com.rcg.commercialData.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommercialResidentPopulationDto {
    private String trdarCd;
    private String trdarCdNm;
    private String stdrYyquCd;
    private Integer totResidPopltnCnt;
    private Integer mlResidPopltnCnt;
    private Integer fmlResidPopltnCnt;
    private Integer age10ResidPopltnCnt;
    private Integer age20ResidPopltnCnt;
    private Integer age30ResidPopltnCnt;
    private Integer age40ResidPopltnCnt;
    private Integer age50ResidPopltnCnt;
    private Integer age60AboveResidPopltnCnt;
    private Integer mlAge10ResidPopltnCnt;
    private Integer mlAge20ResidPopltnCnt;
    private Integer mlAge30ResidPopltnCnt;
    private Integer mlAge40ResidPopltnCnt;
    private Integer mlAge50ResidPopltnCnt;
    private Integer mlAge60AboveResidPopltnCnt;
    private Integer fmlAge10ResidPopltnCnt;
    private Integer fmlAge20ResidPopltnCnt;
    private Integer fmlAge30ResidPopltnCnt;
    private Integer fmlAge40ResidPopltnCnt;
    private Integer fmlAge50ResidPopltnCnt;
    private Integer fmlAge60AboveResidPopltnCnt;
}

