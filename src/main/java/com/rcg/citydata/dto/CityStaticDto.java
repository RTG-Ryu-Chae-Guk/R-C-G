package com.rcg.citydata.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 실시간 항목을 뺀 서울시 도시데이터 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CityStaticDto {
  // 장소 메타
  private String areaNm;            // AREA_NM           핫스팟 장소명
  private String areaCd;            // AREA_CD           핫스팟 코드명

  // 혼잡도 지표
  private String areaCongestLvl;    // AREA_CONGEST_LVL  장소 혼잡도 지표
  private String areaCongestMsg;    // AREA_CONGEST_MSG  장소 혼잡도 지표 관련 메세지

  // 인구 분포 비율 (실시간 제외)
  private String malePpltnRate;     // MALE_PPLTN_RATE    남성 인구 비율
  private String femalePpltnRate;   // FEMALE_PPLTN_RATE  여성 인구 비율
  private String ppltnRate0;        // PPLTN_RATE_0       0~10세 인구 비율
  private String resntPpltnRate;    // RESNT_PPLTN_RATE   상주 인구 비율
  private String nonResntPpltnRate; // NON_RESNT_PPLTN_RATE 비상주 인구 비율

  // 지하철역 메타 (실시간 열차정보 제외)
  private String subStnNm;           // SUB_STN_NM         지하철역명
  private String subStnLine;         // SUB_STN_LINE       지하철역 호선
  private String subStnRaddr;        // SUB_STN_RADDR      지하철역 도로명 주소
  private String subStnJibun;        // SUB_STN_JIBUN      지하철역 지번주소
  private String subStnX;            // SUB_STN_X          지하철역 X 좌표
  private String subStnY;            // SUB_STN_Y          지하철역 Y 좌표
  private String subNtStn;           // SUB_NT_STN         다음역 코드
  private String subBfStn;           // SUB_BF_STN         이전역 코드
  private String subRouteNm;         // SUB_ROUTE_NM       지하철노선명
  private String subLine;            // SUB_LINE           지하철호선

  // 버스정류소 메타 (실시간 도착정보 제외)
  private String busStnId;           // BUS_STN_ID         정류소ID
  private String busArsId;           // BUS_ARS_ID         정류소 고유번호
  private String busStnNm;           // BUS_STN_NM         정류소명
  private String busStnX;            // BUS_STN_X          정류소 X 좌표
  private String busStnY;            // BUS_STN_Y          정류소 Y 좌표

  // 버스 노선 메타 (실시간 도착예정 제외)
  private String rteStnNm;           // RTE_STN_NM         노선 조회 기준 정류장명
  private String rteNm;              // RTE_NM             노선명
  private String rteId;              // RTE_ID             노선ID
  private String rteSect;            // RTE_SECT           노선구간
  private String rteCongest;         // RTE_CONGEST        노선혼잡도
//  private String rteArrvTm;          // RTE_ARRV_TM        노선예상도착시간

  // 상권(업종) 메타 (실시간 결제현황 제외)
  private String rsbLrgCtgr;         // RSB_LRG_CTGR       업종 대분류
  private String rsbMidCtgr;         // RSB_MID_CTGR       업종 중분류
  private String rsbMctCnt;          // RSB_MCT_CNT        업종 가맹점 수
  private String rsbMctTime;         // RSB_MCT_TIME       업종 가맹점 수 업데이트 월

  // 연령별 소비 비율
  private String cmrclMaleRate;      // CMRCL_MALE_RATE      남성 소비 비율
  private String cmrclFemaleRate;    // CMRCL_FEMALE_RATE    여성 소비 비율
  private String cmrcl10Rate;        // CMRCL_10_RATE        10대 이하 소비 비율
  private String cmrcl20Rate;        // CMRCL_20_RATE        20대 소비 비율
  private String cmrcl30Rate;        // CMRCL_30_RATE        30대 소비 비율
  private String cmrcl40Rate;        // CMRCL_40_RATE        40대 소비 비율
  private String cmrcl50Rate;        // CMRCL_50_RATE        50대 소비 비율
  private String cmrcl60Rate;        // CMRCL_60_RATE        60대 이상 소비 비율
  private String cmrclPersonalRate;  // CMRCL_PERSONAL_RATE  개인 소비 비율
  private String cmrclCorporationRate; // CMRCL_CORPORATION_RATE 법인 소비 비율
}