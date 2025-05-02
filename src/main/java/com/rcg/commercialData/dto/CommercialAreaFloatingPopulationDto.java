package com.rcg.commercialData.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommercialAreaFloatingPopulationDto {
    private String trdarCd;
    private String trdarCdNm;
    private String stdrYyquCd;
    private Integer totFlpopCo;
    private Integer mlFlpopCo;
    private Integer fmlFlpopCo;
    private Integer agrde10FlpopCo;
    private Integer agrde20FlpopCo;
    private Integer agrde30FlpopCo;
    private Integer agrde40FlpopCo;
    private Integer agrde50FlpopCo;
    private Integer agrde60FlpopCo;
    private Integer tmzon0006FlpopCo;
    private Integer tmzon0611FlpopCo;
    private Integer tmzon1114FlpopCo;
    private Integer tmzon1417FlpopCo;
    private Integer tmzon1721FlpopCo;
    private Integer tmzon2124FlpopCo;
    private Integer monFlpopCo;
    private Integer tuesFlpopCo;
    private Integer wedFlpopCo;
    private Integer thurFlpopCo;
    private Integer friFlpopCo;
    private Integer satFlpopCo;
    private Integer sunFlpopCo;
}

