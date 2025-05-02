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
@Table(name = "commercial_area_store_status")
public class CommercialAreaStoreStatus {

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

    @CsvBindByName(column = "서비스_업종_코드")
    @Column(name = "svc_induty_cd")
    private String svcIndutyCd;

    @CsvBindByName(column = "서비스_업종_코드_명")
    @Column(name = "svc_induty_cd_nm")
    private String svcIndutyCdNm;

    @CsvBindByName(column = "점포_수")
    @Column(name = "store_co")
    private Integer storeCo;

    @CsvBindByName(column = "유사_업종_점포_수")
    @Column(name = "similr_store_co")
    private Integer similrStoreCo;

    @CsvBindByName(column = "개업_율")
    @Column(name = "open_rt")
    private Double openRt;

    @CsvBindByName(column = "개업_점포_수")
    @Column(name = "open_store_co")
    private Integer openStoreCo;

    @CsvBindByName(column = "폐업_률")
    @Column(name = "close_rt")
    private Double closeRt;

    @CsvBindByName(column = "폐업_점포_수")
    @Column(name = "close_store_co")
    private Integer closeStoreCo;

    @CsvBindByName(column = "프랜차이즈_점포_수")
    @Column(name = "frnchs_store_co")
    private Integer frnchsStoreCo;
}
