// CityStatic.java
package com.rcg.citydata.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "city_static")
public class CityStatic {
  @Id
  @Column(name = "area_cd", length = 20)
  private String areaCd;

  @Column(name = "area_nm", length = 100)
  private String areaNm;

  @Column(name = "area_congest_lvl", length = 20)
  private String areaCongestLvl;

  @Column(name = "area_congest_msg", length = 255)
  private String areaCongestMsg;

  @Column(name = "male_ppltn_rate", length = 10)
  private String malePpltnRate;

  @Column(name = "female_ppltn_rate", length = 10)
  private String femalePpltnRate;

  @Column(name = "resnt_ppltn_rate", length = 10)
  private String resntPpltnRate;

  @Column(name = "non_resnt_ppltn_rate", length = 10)
  private String nonResntPpltnRate;

  @Column(name = "sub_stn_nm", length = 100)
  private String subStnNm;

  @Column(name = "sub_stn_line", length = 20)
  private String subStnLine;

  @Column(name = "sub_stn_raddr", length = 255)
  private String subStnRaddr;

  @Column(name = "sub_stn_jibun", length = 255)
  private String subStnJibun;

  @Column(name = "sub_stn_x", length = 50)
  private String subStnX;

  @Column(name = "sub_stn_y", length = 50)
  private String subStnY;

  @Column(name = "sub_nt_stn", length = 50)
  private String subNtStn;

  @Column(name = "sub_bf_stn", length = 50)
  private String subBfStn;

  @Column(name = "sub_route_nm", length = 100)
  private String subRouteNm;

  @Column(name = "sub_line", length = 50)
  private String subLine;

  @Column(name = "bus_stn_id", length = 50)
  private String busStnId;

  @Column(name = "bus_ars_id", length = 50)
  private String busArsId;

  @Column(name = "bus_stn_nm", length = 100)
  private String busStnNm;

  @Column(name = "bus_stn_x", length = 50)
  private String busStnX;

  @Column(name = "bus_stn_y", length = 50)
  private String busStnY;

  @Column(name = "rte_stn_nm", length = 100)
  private String rteStnNm;

  @Column(name = "rte_nm", length = 50)
  private String rteNm;

  @Column(name = "rte_id", length = 50)
  private String rteId;

  @Column(name = "rte_sect", length = 100)
  private String rteSect;

  @Column(name = "rte_congest", length = 50)
  private String rteCongest;

  @Column(name = "rsb_lrg_ctgr", length = 50)
  private String rsbLrgCtgr;

  @Column(name = "rsb_mid_ctgr", length = 50)
  private String rsbMidCtgr;

  @Column(name = "rsb_mct_cnt", length = 20)
  private String rsbMctCnt;

  @Column(name = "rsb_mct_time", length = 20)
  private String rsbMctTime;

  @JsonProperty("CMRCL_MALE_RATE")
  @Column(name = "cmrcl_male_rate")
  private Double cmrclMaleRate;

  @JsonProperty("CMRCL_FEMALE_RATE")
  @Column(name = "cmrcl_female_rate")
  private Double cmrclFemaleRate;

  @JsonProperty("CMRCL_10_RATE")
  @Column(name = "cmrcl_10_rate")
  private Double cmrcl10Rate;

  @JsonProperty("CMRCL_20_RATE")
  @Column(name = "cmrcl_20_rate")
  private Double cmrcl20Rate;

  @JsonProperty("CMRCL_30_RATE")
  @Column(name = "cmrcl_30_rate")
  private Double cmrcl30Rate;

  @JsonProperty("CMRCL_40_RATE")
  @Column(name = "cmrcl_40_rate")
  private Double cmrcl40Rate;

  @JsonProperty("CMRCL_50_RATE")
  @Column(name = "cmrcl_50_rate")
  private Double cmrcl50Rate;

  @JsonProperty("CMRCL_60_RATE")
  @Column(name = "cmrcl_60_rate")
  private Double cmrcl60Rate;

  @JsonProperty("CMRCL_PERSONAL_RATE")
  @Column(name = "cmrcl_personal_rate")
  private Double cmrclPersonalRate;

  @JsonProperty("CMRCL_CORPORATION_RATE")
  @Column(name = "cmrcl_corporation_rate")
  private Double cmrclCorporationRate;

  @JsonProperty("CMRCL_TIME")
  @Column(name = "cmrcl_time")
  private String cmrclTime;

  @Column(name = "loaded_at")
  private LocalDateTime loadedAt;
}