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
public class CommercialAreaSalesDto {

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

    @JsonProperty("당월_매출금액")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long thsmonSelngAmt;

    @JsonProperty("당월_매출건수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long thsmonSelngCo;

    @JsonProperty("주중_매출금액")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long mdwkSelngAmt;

    @JsonProperty("주말_매출금액")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long wkendSelngAmt;

    @JsonProperty("월요일_매출금액")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long monSelngAmt;

    @JsonProperty("화요일_매출금액")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long tuesSelngAmt;

    @JsonProperty("수요일_매출금액")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long wedSelngAmt;

    @JsonProperty("목요일_매출금액")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long thurSelngAmt;

    @JsonProperty("금요일_매출금액")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long friSelngAmt;

    @JsonProperty("토요일_매출금액")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long satSelngAmt;

    @JsonProperty("일요일_매출금액")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long sunSelngAmt;

    @JsonProperty("00시~06시_매출금액")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long tmzon0006SelngAmt;

    @JsonProperty("06시~11시_매출금액")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long tmzon0611SelngAmt;

    @JsonProperty("11시~14시_매출금액")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long tmzon1114SelngAmt;

    @JsonProperty("14시~17시_매출금액")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long tmzon1417SelngAmt;

    @JsonProperty("17시~21시_매출금액")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long tmzon1721SelngAmt;

    @JsonProperty("21시~24시_매출금액")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long tmzon2124SelngAmt;

    @JsonProperty("남성_매출금액")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long mlSelngAmt;

    @JsonProperty("여성_매출금액")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long fmlSelngAmt;

    @JsonProperty("10대_매출금액")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long agrde10SelngAmt;

    @JsonProperty("20대_매출금액")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long agrde20SelngAmt;

    @JsonProperty("30대_매출금액")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long agrde30SelngAmt;

    @JsonProperty("40대_매출금액")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long agrde40SelngAmt;

    @JsonProperty("50대_매출금액")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long agrde50SelngAmt;

    @JsonProperty("60대_이상_매출금액")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long agrde60AboveSelngAmt;

    @JsonProperty("주중_매출건수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long mdwkSelngCo;

    @JsonProperty("주말_매출건수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long wkendSelngCo;

    @JsonProperty("월요일_매출건수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long monSelngCo;

    @JsonProperty("화요일_매출건수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long tuesSelngCo;

    @JsonProperty("수요일_매출건수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long wedSelngCo;

    @JsonProperty("목요일_매출건수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long thurSelngCo;

    @JsonProperty("금요일_매출건수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long friSelngCo;

    @JsonProperty("토요일_매출건수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long satSelngCo;

    @JsonProperty("일요일_매출건수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long sunSelngCo;

    @JsonProperty("00시~06시_매출건수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long tmzon0006SelngCo;

    @JsonProperty("06시~11시_매출건수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long tmzon0611SelngCo;

    @JsonProperty("11시~14시_매출건수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long tmzon1114SelngCo;

    @JsonProperty("14시~17시_매출건수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long tmzon1417SelngCo;

    @JsonProperty("17시~21시_매출건수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long tmzon1721SelngCo;

    @JsonProperty("21시~24시_매출건수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long tmzon2124SelngCo;

    @JsonProperty("남성_매출건수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long mlSelngCo;

    @JsonProperty("여성_매출건수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long fmlSelngCo;

    @JsonProperty("10대_매출건수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long agrde10SelngCo;

    @JsonProperty("20대_매출건수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long agrde20SelngCo;

    @JsonProperty("30대_매출건수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long agrde30SelngCo;

    @JsonProperty("40대_매출건수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long agrde40SelngCo;

    @JsonProperty("50대_매출건수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long agrde50SelngCo;

    @JsonProperty("60대_매출건수")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long agrde60SelngCo;
}
