package com.rcg.commercialData.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommercialResidentPopulationDto {

    @JsonProperty("상권_코드")
    private String trdarCd;

    @JsonProperty("상권_이름")
    private String trdarCdNm;

    @JsonProperty("기준_년분기_코드")
    private String stdrYyquCd;

    @JsonProperty("총_상주인구_수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long totResidPopltnCnt;

    @JsonProperty("남성_상주인구_수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long mlResidPopltnCnt;

    @JsonProperty("여성_상주인구_수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long fmlResidPopltnCnt;

    @JsonProperty("10대_상주인구_수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long age10ResidPopltnCnt;

    @JsonProperty("20대_상주인구_수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long age20ResidPopltnCnt;

    @JsonProperty("30대_상주인구_수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long age30ResidPopltnCnt;

    @JsonProperty("40대_상주인구_수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long age40ResidPopltnCnt;

    @JsonProperty("50대_상주인구_수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long age50ResidPopltnCnt;

    @JsonProperty("60대_이상_상주인구_수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long age60AboveResidPopltnCnt;

    @JsonProperty("남성_10대_상주인구_수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long mlAge10ResidPopltnCnt;

    @JsonProperty("남성_20대_상주인구_수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long mlAge20ResidPopltnCnt;

    @JsonProperty("남성_30대_상주인구_수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long mlAge30ResidPopltnCnt;

    @JsonProperty("남성_40대_상주인구_수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long mlAge40ResidPopltnCnt;

    @JsonProperty("남성_50대_상주인구_수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long mlAge50ResidPopltnCnt;

    @JsonProperty("남성_60대_이상_상주인구_수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long mlAge60AboveResidPopltnCnt;

    @JsonProperty("여성_10대_상주인구_수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long fmlAge10ResidPopltnCnt;

    @JsonProperty("여성_20대_상주인구_수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long fmlAge20ResidPopltnCnt;

    @JsonProperty("여성_30대_상주인구_수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long fmlAge30ResidPopltnCnt;

    @JsonProperty("여성_40대_상주인구_수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long fmlAge40ResidPopltnCnt;

    @JsonProperty("여성_50대_상주인구_수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long fmlAge50ResidPopltnCnt;

    @JsonProperty("여성_60대_이상_상주인구_수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long fmlAge60AboveResidPopltnCnt;
}
