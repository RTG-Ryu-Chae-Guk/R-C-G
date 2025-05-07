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
public class CommercialAreaFloatingPopulationDto {

    @JsonProperty("상권_코드")
    private String trdarCd;

    @JsonProperty("상권_이름")
    private String trdarCdNm;

    @JsonProperty("기준_년분기_코드")
    private String stdrYyquCd;

    @JsonProperty("총_유동인구수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long totFlpopCo;

    @JsonProperty("남성_유동인구수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long mlFlpopCo;

    @JsonProperty("여성_유동인구수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long fmlFlpopCo;

    @JsonProperty("10대_유동인구수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long agrde10FlpopCo;

    @JsonProperty("20대_유동인구수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long agrde20FlpopCo;

    @JsonProperty("30대_유동인구수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long agrde30FlpopCo;

    @JsonProperty("40대_유동인구수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long agrde40FlpopCo;

    @JsonProperty("50대_유동인구수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long agrde50FlpopCo;

    @JsonProperty("60대_이상_유동인구수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long agrde60FlpopCo;

    @JsonProperty("00시~06시_유동인구수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long tmzon0006FlpopCo;

    @JsonProperty("06시~11시_유동인구수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long tmzon0611FlpopCo;

    @JsonProperty("11시~14시_유동인구수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long tmzon1114FlpopCo;

    @JsonProperty("14시~17시_유동인구수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long tmzon1417FlpopCo;

    @JsonProperty("17시~21시_유동인구수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long tmzon1721FlpopCo;

    @JsonProperty("21시~24시_유동인구수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long tmzon2124FlpopCo;

    @JsonProperty("월요일_유동인구수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long monFlpopCo;

    @JsonProperty("화요일_유동인구수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long tuesFlpopCo;

    @JsonProperty("수요일_유동인구수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long wedFlpopCo;

    @JsonProperty("목요일_유동인구수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long thurFlpopCo;

    @JsonProperty("금요일_유동인구수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long friFlpopCo;

    @JsonProperty("토요일_유동인구수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long satFlpopCo;

    @JsonProperty("일요일_유동인구수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long sunFlpopCo;
}
