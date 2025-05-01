package com.rcg.citydata.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

/**
 * DTO for 전체 서울시 실시간 도시 데이터 응답
 */
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CityDynamicDto {

  private List<BusStnStts> busStnStts;
  private LiveCmrlStts liveCmrlStts;
  private List<LivePpltnStts> livePpltnStts;

  @Data
  @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
  public static class BusStnStts {
    private String busStnX;
    private String busStnY;
    private String busArsId;
    private List<BusDetail> busDetail;
    private String busStnId;
    private String busStnNm;
    private String busResultMsg;
  }

  @Data
  @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
  public static class BusDetail {
    private String rteId;
    private String rteNm;
    private String rteSect;
    private String rteStnNm;
    private String rteArrvTm1;
    private String rteArrvTm2;
    private String rteCongest1;
    private String rteCongest2;
    private String rteArrvStn1;
    private String rteArrvStn2;
  }

  @Data
  @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
  public static class LiveCmrlStts {
    private List<CmrclRsb> cmrclRsb;
    private String cmrclTime;
    private Double cmrcl10Rate;
    private Double cmrcl20Rate;
    private Double cmrcl30Rate;
    private Double cmrcl40Rate;
    private Double cmrcl50Rate;
    private Double cmrcl60Rate;
    private String areaCmrlLvl;
    private Double cmrclMaleRate;
    private Double cmrclFemaleRate;
    private String areaShPaymentCnt;
    private Double cmrclPersonalRate;
    private Double cmrclCorporationRate;
    private Integer areaShPaymentAmtMax;
    private Integer areaShPaymentAmtMin;
  }

  @Data
  @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
  public static class CmrclRsb {
    private Integer rsbMctCnt;
    private String rsbLrgCtgr;
    private String rsbMctTime;
    private String rsbMidCtgr;
    private String rsbPaymentLvl;
    private Integer rsbShPaymentCnt;
    private Integer rsbShPaymentAmtMax;
    private Integer rsbShPaymentAmtMin;
  }

  @Data
  @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
  public static class LivePpltnStts {
    private String areaCd;
    private String areaNm;
    private String fcstYn;
    private List<FcstPpltn> fcstPpltn;
    private String ppltnTime;
    private String replaceYn;
    private Double ppltnRate0;
    private Double ppltnRate10;
    private Double ppltnRate20;
    private Double ppltnRate30;
    private Double ppltnRate40;
    private Double ppltnRate50;
    private Double ppltnRate60;
    private Double ppltnRate70;
    private Integer areaPpltnMax;
    private Integer areaPpltnMin;
    private Double malePpltnRate;
    private String areaCongestLvl;
    private String areaCongestMsg;
    private Double resntPpltnRate;
    private Double femalePpltnRate;
    private Double nonResntPpltnRate;
  }

  @Data
  @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
  public static class FcstPpltn {
    private String fcstTime;
    private Integer fcstPpltnMax;
    private Integer fcstPpltnMin;
    private String fcstCongestLvl;
  }
}
