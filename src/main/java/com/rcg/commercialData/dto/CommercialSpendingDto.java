package com.rcg.commercialData.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommercialSpendingDto {

    @JsonProperty("상권_코드")
    private String trdarCd;

    @JsonProperty("상권_이름")
    private String trdarCdNm;

    @JsonProperty("기준_년분기_코드")
    private String stdrYyquCd;

    @JsonProperty("총_지출_금액")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long totalExpendAmt;

    @JsonProperty("식료품_지출")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long foodExpendAmt;

    @JsonProperty("의류_및_신발_지출")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long clothingExpendAmt;

    @JsonProperty("생활용품_지출")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long livingExpendAmt;

    @JsonProperty("의료비_지출")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long medicalExpendAmt;

    @JsonProperty("교통_지출")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long transportExpendAmt;

    @JsonProperty("여가_지출")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long leisureExpendAmt;

    @JsonProperty("문화_지출")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long cultureExpendAmt;

    @JsonProperty("교육_지출")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long educationExpendAmt;

    @JsonProperty("유흥_지출")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private Long entertainmentExpendAmt;
}
