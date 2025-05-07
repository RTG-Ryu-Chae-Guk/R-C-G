package com.rcg.commercialData.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommercialAreaStoreStatusDto {

    @JsonProperty("상권_코드")
    private String trdarCd;

    @JsonProperty("상권_이름")
    private String trdarCdNm;

    @JsonProperty("기준_년분기_코드")
    private String stdrYyquCd;

    @JsonProperty("서비스업종_코드")
    private String svcIndutyCd;

    @JsonProperty("서비스업종_이름")
    private String svcIndutyCdNm;

    @JsonProperty("점포_수")
    private Integer storeCo;

    @JsonProperty("유사_업종_점포_수")
    private Integer similrStoreCo;

    @JsonProperty("개업률(%)")
    private Double openRt;

    @JsonProperty("개업_점포_수")
    private Integer openStoreCo;

    @JsonProperty("폐업률(%)")
    private Double closeRt;

    @JsonProperty("폐업_점포_수")
    private Integer closeStoreCo;

    @JsonProperty("프랜차이즈_점포_수")
    private Integer frnchsStoreCo;
}
