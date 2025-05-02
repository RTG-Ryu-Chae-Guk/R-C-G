package com.rcg.commercialData.entity;

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
@Table(name = "commercial_area_floating_population")
public class CommercialAreaFloatingPopulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @CsvBindByName(column = "총_유동인구_수")
    @Column(name = "tot_flpop_co")
    private Integer totFlpopCo;

    @CsvBindByName(column = "남성_유동인구_수")
    @Column(name = "ml_flpop_co")
    private Integer mlFlpopCo;

    @CsvBindByName(column = "여성_유동인구_수")
    @Column(name = "fml_flpop_co")
    private Integer fmlFlpopCo;

    @CsvBindByName(column = "연령대_10_유동인구_수")
    @Column(name = "agrde_10_flpop_co")
    private Integer agrde10FlpopCo;

    @CsvBindByName(column = "연령대_20_유동인구_수")
    @Column(name = "agrde_20_flpop_co")
    private Integer agrde20FlpopCo;

    @CsvBindByName(column = "연령대_30_유동인구_수")
    @Column(name = "agrde_30_flpop_co")
    private Integer agrde30FlpopCo;

    @CsvBindByName(column = "연령대_40_유동인구_수")
    @Column(name = "agrde_40_flpop_co")
    private Integer agrde40FlpopCo;

    @CsvBindByName(column = "연령대_50_유동인구_수")
    @Column(name = "agrde_50_flpop_co")
    private Integer agrde50FlpopCo;

    @CsvBindByName(column = "연령대_60_이상_유동인구_수")
    @Column(name = "agrde_60_flpop_co")
    private Integer agrde60FlpopCo;

    @CsvBindByName(column = "시간대_00_06_유동인구_수")
    @Column(name = "tmzon_00_06_flpop_co")
    private Integer tmzon0006FlpopCo;

    @CsvBindByName(column = "시간대_06_11_유동인구_수")
    @Column(name = "tmzon_06_11_flpop_co")
    private Integer tmzon0611FlpopCo;

    @CsvBindByName(column = "시간대_11_14_유동인구_수")
    @Column(name = "tmzon_11_14_flpop_co")
    private Integer tmzon1114FlpopCo;

    @CsvBindByName(column = "시간대_14_17_유동인구_수")
    @Column(name = "tmzon_14_17_flpop_co")
    private Integer tmzon1417FlpopCo;

    @CsvBindByName(column = "시간대_17_21_유동인구_수")
    @Column(name = "tmzon_17_21_flpop_co")
    private Integer tmzon1721FlpopCo;

    @CsvBindByName(column = "시간대_21_24_유동인구_수")
    @Column(name = "tmzon_21_24_flpop_co")
    private Integer tmzon2124FlpopCo;

    @CsvBindByName(column = "월요일_유동인구_수")
    @Column(name = "mon_flpop_co")
    private Integer monFlpopCo;

    @CsvBindByName(column = "화요일_유동인구_수")
    @Column(name = "tues_flpop_co")
    private Integer tuesFlpopCo;

    @CsvBindByName(column = "수요일_유동인구_수")
    @Column(name = "wed_flpop_co")
    private Integer wedFlpopCo;

    @CsvBindByName(column = "목요일_유동인구_수")
    @Column(name = "thur_flpop_co")
    private Integer thurFlpopCo;

    @CsvBindByName(column = "금요일_유동인구_수")
    @Column(name = "fri_flpop_co")
    private Integer friFlpopCo;

    @CsvBindByName(column = "토요일_유동인구_수")
    @Column(name = "sat_flpop_co")
    private Integer satFlpopCo;

    @CsvBindByName(column = "일요일_유동인구_수")
    @Column(name = "sun_flpop_co")
    private Integer sunFlpopCo;
}