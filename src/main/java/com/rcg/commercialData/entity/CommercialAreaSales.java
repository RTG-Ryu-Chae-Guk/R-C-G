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
@Table(name = "commercial_area_sales")
public class CommercialAreaSales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // surrogate key

    @CsvBindByName(column = "stdr_yyqu_cd")
    @Column(name = "stdr_yyqu_cd")
    private String stdrYyquCd;

    @CsvBindByName(column = "trdar_cd")
    @Column(name = "trdar_cd")
    private String trdarCd;

    @CsvBindByName(column = "trdar_cd_nm")
    @Column(name = "trdar_cd_nm")
    private String trdarCdNm;

    @CsvBindByName(column = "trdar_se_cd")
    @Column(name = "trdar_se_cd")
    private String trdarSeCd;

    @CsvBindByName(column = "trdar_se_cd_nm")
    @Column(name = "trdar_se_cd_nm")
    private String trdarSeCdNm;

    @CsvBindByName(column = "svc_induty_cd")
    @Column(name = "svc_induty_cd")
    private String svcIndutyCd;

    @CsvBindByName(column = "svc_induty_cd_nm")
    @Column(name = "svc_induty_cd_nm")
    private String svcIndutyCdNm;

    @CsvBindByName(column = "thsmon_selng_amt")
    @Column(name = "thsmon_selng_amt")
    private Long thsmonSelngAmt;

    @CsvBindByName(column = "thsmon_selng_co")
    @Column(name = "thsmon_selng_co")
    private Long thsmonSelngCo;

    @CsvBindByName(column = "mdwk_selng_amt")
    @Column(name = "mdwk_selng_amt")
    private Long mdwkSelngAmt;

    @CsvBindByName(column = "wkend_selng_amt")
    @Column(name = "wkend_selng_amt")
    private Long wkendSelngAmt;

    @CsvBindByName(column = "mon_selng_amt")
    @Column(name = "mon_selng_amt")
    private Long monSelngAmt;

    @CsvBindByName(column = "tues_selng_amt")
    @Column(name = "tues_selng_amt")
    private Long tuesSelngAmt;

    @CsvBindByName(column = "wed_selng_amt")
    @Column(name = "wed_selng_amt")
    private Long wedSelngAmt;

    @CsvBindByName(column = "thur_selng_amt")
    @Column(name = "thur_selng_amt")
    private Long thurSelngAmt;

    @CsvBindByName(column = "fri_selng_amt")
    @Column(name = "fri_selng_amt")
    private Long friSelngAmt;

    @CsvBindByName(column = "sat_selng_amt")
    @Column(name = "sat_selng_amt")
    private Long satSelngAmt;

    @CsvBindByName(column = "sun_selng_amt")
    @Column(name = "sun_selng_amt")
    private Long sunSelngAmt;

    @CsvBindByName(column = "tmzon_00_06_selng_amt")
    @Column(name = "tmzon_00_06_selng_amt")
    private Long tmzon0006SelngAmt;

    @CsvBindByName(column = "tmzon_06_11_selng_amt")
    @Column(name = "tmzon_06_11_selng_amt")
    private Long tmzon0611SelngAmt;

    @CsvBindByName(column = "tmzon_11_14_selng_amt")
    @Column(name = "tmzon_11_14_selng_amt")
    private Long tmzon1114SelngAmt;

    @CsvBindByName(column = "tmzon_14_17_selng_amt")
    @Column(name = "tmzon_14_17_selng_amt")
    private Long tmzon1417SelngAmt;

    @CsvBindByName(column = "tmzon_17_21_selng_amt")
    @Column(name = "tmzon_17_21_selng_amt")
    private Long tmzon1721SelngAmt;

    @CsvBindByName(column = "tmzon_21_24_selng_amt")
    @Column(name = "tmzon_21_24_selng_amt")
    private Long tmzon2124SelngAmt;

    @CsvBindByName(column = "ml_selng_amt")
    @Column(name = "ml_selng_amt")
    private Long mlSelngAmt;

    @CsvBindByName(column = "fml_selng_amt")
    @Column(name = "fml_selng_amt")
    private Long fmlSelngAmt;

    @CsvBindByName(column = "agrde_10_selng_amt")
    @Column(name = "agrde_10_selng_amt")
    private Long agrde10SelngAmt;

    @CsvBindByName(column = "agrde_20_selng_amt")
    @Column(name = "agrde_20_selng_amt")
    private Long agrde20SelngAmt;

    @CsvBindByName(column = "agrde_30_selng_amt")
    @Column(name = "agrde_30_selng_amt")
    private Long agrde30SelngAmt;

    @CsvBindByName(column = "agrde_40_selng_amt")
    @Column(name = "agrde_40_selng_amt")
    private Long agrde40SelngAmt;

    @CsvBindByName(column = "agrde_50_selng_amt")
    @Column(name = "agrde_50_selng_amt")
    private Long agrde50SelngAmt;

    @CsvBindByName(column = "agrde_60_above_selng_amt")
    @Column(name = "agrde_60_above_selng_amt")
    private Long agrde60AboveSelngAmt;

    @CsvBindByName(column = "mdwk_selng_co")
    @Column(name = "mdwk_selng_co")
    private Long mdwkSelngCo;

    @CsvBindByName(column = "wkend_selng_co")
    @Column(name = "wkend_selng_co")
    private Long wkendSelngCo;

    @CsvBindByName(column = "mon_selng_co")
    @Column(name = "mon_selng_co")
    private Long monSelngCo;

    @CsvBindByName(column = "tues_selng_co")
    @Column(name = "tues_selng_co")
    private Long tuesSelngCo;

    @CsvBindByName(column = "wed_selng_co")
    @Column(name = "wed_selng_co")
    private Long wedSelngCo;

    @CsvBindByName(column = "thur_selng_co")
    @Column(name = "thur_selng_co")
    private Long thurSelngCo;

    @CsvBindByName(column = "fri_selng_co")
    @Column(name = "fri_selng_co")
    private Long friSelngCo;

    @CsvBindByName(column = "sat_selng_co")
    @Column(name = "sat_selng_co")
    private Long satSelngCo;

    @CsvBindByName(column = "sun_selng_co")
    @Column(name = "sun_selng_co")
    private Long sunSelngCo;

    @CsvBindByName(column = "tmzon_00_06_selng_co")
    @Column(name = "tmzon_00_06_selng_co")
    private Long tmzon0006SelngCo;

    @CsvBindByName(column = "tmzon_06_11_selng_co")
    @Column(name = "tmzon_06_11_selng_co")
    private Long tmzon0611SelngCo;

    @CsvBindByName(column = "tmzon_11_14_selng_co")
    @Column(name = "tmzon_11_14_selng_co")
    private Long tmzon1114SelngCo;

    @CsvBindByName(column = "tmzon_14_17_selng_co")
    @Column(name = "tmzon_14_17_selng_co")
    private Long tmzon1417SelngCo;

    @CsvBindByName(column = "tmzon_17_21_selng_co")
    @Column(name = "tmzon_17_21_selng_co")
    private Long tmzon1721SelngCo;

    @CsvBindByName(column = "tmzon_21_24_selng_co")
    @Column(name = "tmzon_21_24_selng_co")
    private Long tmzon2124SelngCo;

    @CsvBindByName(column = "ml_selng_co")
    @Column(name = "ml_selng_co")
    private Long mlSelngCo;

    @CsvBindByName(column = "fml_selng_co")
    @Column(name = "fml_selng_co")
    private Long fmlSelngCo;

    @CsvBindByName(column = "agrde_10_selng_co")
    @Column(name = "agrde_10_selng_co")
    private Long agrde10SelngCo;

    @CsvBindByName(column = "agrde_20_selng_co")
    @Column(name = "agrde_20_selng_co")
    private Long agrde20SelngCo;

    @CsvBindByName(column = "agrde_30_selng_co")
    @Column(name = "agrde_30_selng_co")
    private Long agrde30SelngCo;

    @CsvBindByName(column = "agrde_40_selng_co")
    @Column(name = "agrde_40_selng_co")
    private Long agrde40SelngCo;

    @CsvBindByName(column = "agrde_50_selng_co")
    @Column(name = "agrde_50_selng_co")
    private Long agrde50SelngCo;

    @CsvBindByName(column = "agrde_60_selng_co")
    @Column(name = "agrde_60_selng_co")
    private Long agrde60SelngCo;

}