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

    @CsvBindByName(column = "상권_코드")
    @Column(name = "trdar_cd")
    private String trdarCd; // 상권 코드

    @CsvBindByName(column = "기준_년분기_코드")
    @Column(name = "stdr_yyqu_cd")
    private String stdrYyquCd; // 기준 년분기 코드

    @CsvBindByName(column = "상권_코드_명")
    @Column(name = "trdar_cd_nm")
    private String trdarCdNm; // 상권 코드명

    @CsvBindByName(column = "상권_구분_코드")
    @Column(name = "trdar_se_cd")
    private String trdarSeCd; // 상권 구분 코드

    @CsvBindByName(column = "상권_구분_코드_명")
    @Column(name = "trdar_se_cd_nm")
    private String trdarSeCdNm; // 상권 구분 코드명

    @CsvBindByName(column = "서비스_업종_코드")
    @Column(name = "svc_induty_cd")
    private String svcIndutyCd; // 서비스 업종 코드

    @CsvBindByName(column = "서비스_업종_코드_명")
    @Column(name = "svc_induty_cd_nm")
    private String svcIndutyCdNm; // 서비스 업종명

    @CsvBindByName(column = "당월_매출_금액")
    @Column(name = "thsmon_selng_amt")
    private Long thsmonSelngAmt; // 당월 매출 금액

    @CsvBindByName(column = "당월_매출_건수")
    @Column(name = "thsmon_selng_co")
    private Long thsmonSelngCo; // 당월 매출 건수

    @CsvBindByName(column = "주중_매출_금액")
    @Column(name = "mdwk_selng_amt")
    private Long mdwkSelngAmt; // 주중 매출 금액

    @CsvBindByName(column = "주말_매출_금액")
    @Column(name = "wkend_selng_amt")
    private Long wkendSelngAmt; // 주말 매출 금액

    @CsvBindByName(column = "월요일_매출_금액")
    @Column(name = "mon_selng_amt")
    private Long monSelngAmt;

    @CsvBindByName(column = "화요일_매출_금액")
    @Column(name = "tues_selng_amt")
    private Long tuesSelngAmt;

    @CsvBindByName(column = "수요일_매출_금액")
    @Column(name = "wed_selng_amt")
    private Long wedSelngAmt;

    @CsvBindByName(column = "목요일_매출_금액")
    @Column(name = "thur_selng_amt")
    private Long thurSelngAmt;

    @CsvBindByName(column = "금요일_매출_금액")
    @Column(name = "fri_selng_amt")
    private Long friSelngAmt;

    @CsvBindByName(column = "토요일_매출_금액")
    @Column(name = "sat_selng_amt")
    private Long satSelngAmt;

    @CsvBindByName(column = "일요일_매출_금액")
    @Column(name = "sun_selng_amt")
    private Long sunSelngAmt;

    @CsvBindByName(column = "시간대_00~06_매출_금액")
    @Column(name = "tmzon_00_06_selng_amt")
    private Long tmzon0006SelngAmt;

    @CsvBindByName(column = "시간대_06~11_매출_금액")
    @Column(name = "tmzon_06_11_selng_amt")
    private Long tmzon0611SelngAmt;

    @CsvBindByName(column = "시간대_11~14_매출_금액")
    @Column(name = "tmzon_11_14_selng_amt")
    private Long tmzon1114SelngAmt;

    @CsvBindByName(column = "시간대_14~17_매출_금액")
    @Column(name = "tmzon_14_17_selng_amt")
    private Long tmzon1417SelngAmt;

    @CsvBindByName(column = "시간대_17~21_매출_금액")
    @Column(name = "tmzon_17_21_selng_amt")
    private Long tmzon1721SelngAmt;

    @CsvBindByName(column = "시간대_21~24_매출_금액")
    @Column(name = "tmzon_21_24_selng_amt")
    private Long tmzon2124SelngAmt;

    @CsvBindByName(column = "남성_매출_금액")
    @Column(name = "ml_selng_amt")
    private Long mlSelngAmt;

    @CsvBindByName(column = "여성_매출_금액")
    @Column(name = "fml_selng_amt")
    private Long fmlSelngAmt;

    @CsvBindByName(column = "연령대_10_매출_금액")
    @Column(name = "agrde_10_selng_amt")
    private Long agrde10SelngAmt;

    @CsvBindByName(column = "연령대_20_매출_금액")
    @Column(name = "agrde_20_selng_amt")
    private Long agrde20SelngAmt;

    @CsvBindByName(column = "연령대_30_매출_금액")
    @Column(name = "agrde_30_selng_amt")
    private Long agrde30SelngAmt;

    @CsvBindByName(column = "연령대_40_매출_금액")
    @Column(name = "agrde_40_selng_amt")
    private Long agrde40SelngAmt;

    @CsvBindByName(column = "연령대_50_매출_금액")
    @Column(name = "agrde_50_selng_amt")
    private Long agrde50SelngAmt;

    @CsvBindByName(column = "연령대_60_이상_매출_금액")
    @Column(name = "agrde_60_above_selng_amt")
    private Long agrde60AboveSelngAmt;

    @CsvBindByName(column = "주중_매출_건수")
    @Column(name = "mdwk_selng_co")
    private Long mdwkSelngCo;

    @CsvBindByName(column = "주말_매출_건수")
    @Column(name = "wkend_selng_co")
    private Long wkendSelngCo;

    @CsvBindByName(column = "월요일_매출_건수")
    @Column(name = "mon_selng_co")
    private Long monSelngCo;

    @CsvBindByName(column = "화요일_매출_건수")
    @Column(name = "tues_selng_co")
    private Long tuesSelngCo;

    @CsvBindByName(column = "수요일_매출_건수")
    @Column(name = "wed_selng_co")
    private Long wedSelngCo;

    @CsvBindByName(column = "목요일_매출_건수")
    @Column(name = "thur_selng_co")
    private Long thurSelngCo;

    @CsvBindByName(column = "금요일_매출_건수")
    @Column(name = "fri_selng_co")
    private Long friSelngCo;

    @CsvBindByName(column = "토요일_매출_건수")
    @Column(name = "sat_selng_co")
    private Long satSelngCo;

    @CsvBindByName(column = "일요일_매출_건수")
    @Column(name = "sun_selng_co")
    private Long sunSelngCo;

    @CsvBindByName(column = "시간대_건수~06_매출_건수")
    @Column(name = "tmzon_00_06_selng_co")
    private Long tmzon0006SelngCo;

    @CsvBindByName(column = "시간대_건수~11_매출_건수")
    @Column(name = "tmzon_06_11_selng_co")
    private Long tmzon0611SelngCo;

    @CsvBindByName(column = "시간대_건수~14_매출_건수")
    @Column(name = "tmzon_11_14_selng_co")
    private Long tmzon1114SelngCo;

    @CsvBindByName(column = "시간대_건수~17_매출_건수")
    @Column(name = "tmzon_14_17_selng_co")
    private Long tmzon1417SelngCo;

    @CsvBindByName(column = "시간대_건수~21_매출_건수")
    @Column(name = "tmzon_17_21_selng_co")
    private Long tmzon1721SelngCo;

    @CsvBindByName(column = "시간대_건수~24_매출_건수")
    @Column(name = "tmzon_21_24_selng_co")
    private Long tmzon2124SelngCo;

    @CsvBindByName(column = "남성_매출_건수")
    @Column(name = "ml_selng_co")
    private Long mlSelngCo;

    @CsvBindByName(column = "여성_매출_건수")
    @Column(name = "fml_selng_co")
    private Long fmlSelngCo;

    @CsvBindByName(column = "연령대_10_매출_건수")
    @Column(name = "agrde_10_selng_co")
    private Long agrde10SelngCo;

    @CsvBindByName(column = "연령대_20_매출_건수")
    @Column(name = "agrde_20_selng_co")
    private Long agrde20SelngCo;

    @CsvBindByName(column = "연령대_30_매출_건수")
    @Column(name = "agrde_30_selng_co")
    private Long agrde30SelngCo;

    @CsvBindByName(column = "연령대_40_매출_건수")
    @Column(name = "agrde_40_selng_co")
    private Long agrde40SelngCo;

    @CsvBindByName(column = "연령대_50_매출_건수")
    @Column(name = "agrde_50_selng_co")
    private Long agrde50SelngCo;

    @CsvBindByName(column = "연령대_60_이상_매출_건수")
    @Column(name = "agrde_60_selng_co")
    private Long agrde60SelngCo;
}