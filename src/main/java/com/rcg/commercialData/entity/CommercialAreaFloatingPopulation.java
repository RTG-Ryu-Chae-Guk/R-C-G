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

    @CsvBindByName(column = "stdr_yyqu_cd")
    @Column(name = "stdr_yyqu_cd")
    private String stdrYyquCd;

    @CsvBindByName(column = "trdar_se_cd")
    @Column(name = "trdar_se_cd")
    private String trdarSeCd;

    @CsvBindByName(column = "trdar_se_cd_nm")
    @Column(name = "trdar_se_cd_nm")
    private String trdarSeCdNm;

    @CsvBindByName(column = "trdar_cd")
    @Column(name = "trdar_cd")
    private String trdarCd;

    @CsvBindByName(column = "trdar_cd_nm")
    @Column(name = "trdar_cd_nm")
    private String trdarCdNm;

    @CsvBindByName(column = "tot_flpop_co")
    @Column(name = "tot_flpop_co")
    private Integer totFlpopCo;

    @CsvBindByName(column = "ml_flpop_co")
    @Column(name = "ml_flpop_co")
    private Integer mlFlpopCo;

    @CsvBindByName(column = "fml_flpop_co")
    @Column(name = "fml_flpop_co")
    private Integer fmlFlpopCo;

    @CsvBindByName(column = "agrde_10_flpop_co")
    @Column(name = "agrde_10_flpop_co")
    private Integer agrde10FlpopCo;

    @CsvBindByName(column = "agrde_20_flpop_co")
    @Column(name = "agrde_20_flpop_co")
    private Integer agrde20FlpopCo;

    @CsvBindByName(column = "agrde_30_flpop_co")
    @Column(name = "agrde_30_flpop_co")
    private Integer agrde30FlpopCo;

    @CsvBindByName(column = "agrde_40_flpop_co")
    @Column(name = "agrde_40_flpop_co")
    private Integer agrde40FlpopCo;

    @CsvBindByName(column = "agrde_50_flpop_co")
    @Column(name = "agrde_50_flpop_co")
    private Integer agrde50FlpopCo;

    @CsvBindByName(column = "agrde_60_flpop_co")
    @Column(name = "agrde_60_flpop_co")
    private Integer agrde60FlpopCo;

    @CsvBindByName(column = "tmzon_00_06_flpop_co")
    @Column(name = "tmzon_00_06_flpop_co")
    private Integer tmzon0006FlpopCo;

    @CsvBindByName(column = "tmzon_06_11_flpop_co")
    @Column(name = "tmzon_06_11_flpop_co")
    private Integer tmzon0611FlpopCo;

    @CsvBindByName(column = "tmzon_11_14_flpop_co")
    @Column(name = "tmzon_11_14_flpop_co")
    private Integer tmzon1114FlpopCo;

    @CsvBindByName(column = "tmzon_14_17_flpop_co")
    @Column(name = "tmzon_14_17_flpop_co")
    private Integer tmzon1417FlpopCo;

    @CsvBindByName(column = "tmzon_17_21_flpop_co")
    @Column(name = "tmzon_17_21_flpop_co")
    private Integer tmzon1721FlpopCo;

    @CsvBindByName(column = "tmzon_21_24_flpop_co")
    @Column(name = "tmzon_21_24_flpop_co")
    private Integer tmzon2124FlpopCo;

    @CsvBindByName(column = "mon_flpop_co")
    @Column(name = "mon_flpop_co")
    private Integer monFlpopCo;

    @CsvBindByName(column = "tues_flpop_co")
    @Column(name = "tues_flpop_co")
    private Integer tuesFlpopCo;

    @CsvBindByName(column = "wed_flpop_co")
    @Column(name = "wed_flpop_co")
    private Integer wedFlpopCo;

    @CsvBindByName(column = "thur_flpop_co")
    @Column(name = "thur_flpop_co")
    private Integer thurFlpopCo;

    @CsvBindByName(column = "fri_flpop_co")
    @Column(name = "fri_flpop_co")
    private Integer friFlpopCo;

    @CsvBindByName(column = "sat_flpop_co")
    @Column(name = "sat_flpop_co")
    private Integer satFlpopCo;

    @CsvBindByName(column = "sun_flpop_co")
    @Column(name = "sun_flpop_co")
    private Integer sunFlpopCo;
}
