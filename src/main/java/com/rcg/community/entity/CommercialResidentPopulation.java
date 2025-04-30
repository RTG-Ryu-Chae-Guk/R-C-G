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
@Table(name = "commercial_resident_population")
public class CommercialResidentPopulation {

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

    @CsvBindByName(column = "총_상주인구_수")
    @Column(name = "tot_resid_popltn_cnt")
    private Integer totResidPopltnCnt;

    @CsvBindByName(column = "남성_상주인구_수")
    @Column(name = "ml_resid_popltn_cnt")
    private Integer mlResidPopltnCnt;

    @CsvBindByName(column = "여성_상주인구_수")
    @Column(name = "fml_resid_popltn_cnt")
    private Integer fmlResidPopltnCnt;

    @CsvBindByName(column = "연령대_10_상주인구_수")
    @Column(name = "age10_resid_popltn_cnt")
    private Integer age10ResidPopltnCnt;

    @CsvBindByName(column = "연령대_20_상주인구_수")
    @Column(name = "age20_resid_popltn_cnt")
    private Integer age20ResidPopltnCnt;

    @CsvBindByName(column = "연령대_30_상주인구_수")
    @Column(name = "age30_resid_popltn_cnt")
    private Integer age30ResidPopltnCnt;

    @CsvBindByName(column = "연령대_40_상주인구_수")
    @Column(name = "age40_resid_popltn_cnt")
    private Integer age40ResidPopltnCnt;

    @CsvBindByName(column = "연령대_50_상주인구_수")
    @Column(name = "age50_resid_popltn_cnt")
    private Integer age50ResidPopltnCnt;

    @CsvBindByName(column = "연령대_60_이상_상주인구_수")
    @Column(name = "age60_above_resid_popltn_cnt")
    private Integer age60AboveResidPopltnCnt;

    @CsvBindByName(column = "남성연령대_10_상주인구_수")
    @Column(name = "ml_age10_resid_popltn_cnt")
    private Integer mlAge10ResidPopltnCnt;

    @CsvBindByName(column = "남성연령대_20_상주인구_수")
    @Column(name = "ml_age20_resid_popltn_cnt")
    private Integer mlAge20ResidPopltnCnt;

    @CsvBindByName(column = "남성연령대_30_상주인구_수")
    @Column(name = "ml_age30_resid_popltn_cnt")
    private Integer mlAge30ResidPopltnCnt;

    @CsvBindByName(column = "남성연령대_40_상주인구_수")
    @Column(name = "ml_age40_resid_popltn_cnt")
    private Integer mlAge40ResidPopltnCnt;

    @CsvBindByName(column = "남성연령대_50_상주인구_수")
    @Column(name = "ml_age50_resid_popltn_cnt")
    private Integer mlAge50ResidPopltnCnt;

    @CsvBindByName(column = "남성연령대_60_이상_상주인구_수")
    @Column(name = "ml_age60_above_resid_popltn_cnt")
    private Integer mlAge60AboveResidPopltnCnt;

    @CsvBindByName(column = "여성연령대_10_상주인구_수")
    @Column(name = "fml_age10_resid_popltn_cnt")
    private Integer fmlAge10ResidPopltnCnt;

    @CsvBindByName(column = "여성연령대_20_상주인구_수")
    @Column(name = "fml_age20_resid_popltn_cnt")
    private Integer fmlAge20ResidPopltnCnt;

    @CsvBindByName(column = "여성연령대_30_상주인구_수")
    @Column(name = "fml_age30_resid_popltn_cnt")
    private Integer fmlAge30ResidPopltnCnt;

    @CsvBindByName(column = "여성연령대_40_상주인구_수")
    @Column(name = "fml_age40_resid_popltn_cnt")
    private Integer fmlAge40ResidPopltnCnt;

    @CsvBindByName(column = "여성연령대_50_상주인구_수")
    @Column(name = "fml_age50_resid_popltn_cnt")
    private Integer fmlAge50ResidPopltnCnt;

    @CsvBindByName(column = "여성연령대_60_이상_상주인구_수")
    @Column(name = "fml_age60_above_resid_popltn_cnt")
    private Integer fmlAge60AboveResidPopltnCnt;
}