package com.rcg.community.entity;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "commercial_spending")
public class CommercialSpending {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @CsvBindByName(column = "기준_년분기_코드")
    @Column(name = "stdr_yyqu_cd")
    private String stdrYyquCd;

    @CsvBindByName(column = "상권_구분_코드")
    @Column(name = "trdar_se_cd")
    private String trdarSeCd;

    @CsvBindByName(column = "상권_구분_코드_명")
    @Column(name = "trdar_se_cd_nm")
    private String trdarSeCdNm;

    @CsvBindByName(column = "상권_코드")
    @Column(name = "trdar_cd")
    private String trdarCd;

    @CsvBindByName(column = "상권_코드_명")
    @Column(name = "trdar_cd_nm")
    private String trdarCdNm;

    @CsvBindByName(column = "지출_총금액")
    @Column(name = "total_expend_amt")
    private Long totalExpendAmt;

    @CsvBindByName(column = "식료품_지출_총금액")
    @Column(name = "food_expend_amt")
    private Long foodExpendAmt;

    @CsvBindByName(column = "의류_신발_지출_총금액")
    @Column(name = "clothing_expend_amt")
    private Long clothingExpendAmt;

    @CsvBindByName(column = "생활용품_지출_총금액")
    @Column(name = "living_expend_amt")
    private Long livingExpendAmt;

    @CsvBindByName(column = "의료비_지출_총금액")
    @Column(name = "medical_expend_amt")
    private Long medicalExpendAmt;

    @CsvBindByName(column = "교통_지출_총금액")
    @Column(name = "transport_expend_amt")
    private Long transportExpendAmt;

    @CsvBindByName(column = "여가_지출_총금액")
    @Column(name = "leisure_expend_amt")
    private Long leisureExpendAmt;

    @CsvBindByName(column = "문화_지출_총금액")
    @Column(name = "culture_expend_amt")
    private Long cultureExpendAmt;

    @CsvBindByName(column = "교육_지출_총금액")
    @Column(name = "education_expend_amt")
    private Long educationExpendAmt;

    @CsvBindByName(column = "유흥_지출_총금액")
    @Column(name = "entertainment_expend_amt")
    private Long entertainmentExpendAmt;
}
