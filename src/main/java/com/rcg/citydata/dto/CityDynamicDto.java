package com.rcg.citydata.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * DTO for 전체 서울시 실시간 도시 데이터 응답
 */
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CityDynamicDto {

  // private LiveCmrlStts liveCmrlStts;
  private List<LivePpltnStts> livePpltnStts;

  // 주차장 리스트 형태로 수정
  @JsonProperty("PRK_STTS")
  private List<ParkingLotStts> parkingLotStts;

  @Schema(description = "도로 소통 상태 목록")
  private List<RoadTrafficSttsDto> roadTrafficSttsList;

  // 도로 현황 메시지 (단일 문자열)
  private String roadMsg;


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


  // 실시간 인구비율
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

  // 인구예측 DTO
  @Data
  @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
  public static class FcstPpltn {
    @Schema(description = "예측 시간 (예: 1300은 오후 1시)", example = "1300")
    private String fcstTime;

    @Schema(description = "예측 최대 인구 수", example = "1200")
    private Integer fcstPpltnMax;

    @Schema(description = "예측 최소 인구 수", example = "800")
    private Integer fcstPpltnMin;

    @Schema(description = "예측 혼잡도 수준 (예: 여유, 보통, 붐빔)", example = "보통")
    private String fcstCongestLvl;
  }


  // 주차장 DTO
  @Data
  @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
  @Schema(description = "주차장 상태 정보")
  public static class ParkingLotStts {

    @Schema(description = "주차장명", example = "서울시청 주차장")
    private String prkNm;

    @Schema(description = "주차장 코드", example = "1584732")
    private String prkCd;

    @Schema(description = "주차장 구분", example = "공영")
    private String prkType;

    @Schema(description = "수용 가능 면수", example = "200")
    private Integer cpcty;

    @Schema(description = "현재 주차 가능 면수", example = "50")
    private Integer curPrkCnt;

    @Schema(description = "주차 데이터 업데이트 시간", example = "2024-05-16 14:30")
    private String curPrkTime;

    @Schema(description = "실시간 여부 (Y/N)", example = "Y")
    private String curPrkYn;

    @Schema(description = "유/무료 여부 (Y: 유료, N: 무료)", example = "Y")
    private String payYn;

    @Schema(description = "기본 요금 (원)", example = "1000")
    private Double rates;

    @Schema(description = "기본 단위 시간 (분)", example = "30")
    private Double timeRates;

    @Schema(description = "추가 요금 (원)", example = "500")
    private Double addRates;

    @Schema(description = "추가 단위 시간 (분)", example = "10")
    private Double addTimeRates;

    @Schema(description = "주소", example = "서울특별시 중구 세종대로 110")
    private String address;

    @Schema(description = "경도", example = "126.9779692")
    private Double lng;

    @Schema(description = "위도", example = "37.566535")
    private Double lat;
  }

  @Data
  @Schema(description = "도로 소통 상태 정보")
  public class RoadTrafficSttsDto {

    @Schema(description = "도로 링크 ID", example = "1000000302")
    private String linkId;

    @Schema(description = "도로명", example = "종로")
    private String roadNm;

    @Schema(description = "시작 노드 코드", example = "1000007700")
    private String startNdCd;

    @Schema(description = "시작 노드 이름", example = "종로1가")
    private String startNdNm;

    @Schema(description = "시작 노드 좌표", example = "126.9822109960776402_37.5701758601934444")
    private String startNdXy;

    @Schema(description = "종료 노드 코드", example = "1000008900")
    private String endNdCd;

    @Schema(description = "종료 노드 이름", example = "SK빌딩")
    private String endNdNm;

    @Schema(description = "종료 노드 좌표", example = "126.9810231163645540_37.5702165858212354")
    private String endNdXy;

    @Schema(description = "도로 구간 길이 (미터)", example = "151.0")
    private Double dist;

    @Schema(description = "도로 구간 평균 속도 (km/h)", example = "29.0")
    private Double spd;

    @Schema(description = "도로 구간 소통 지표 (예: 원활, 서행, 정체)", example = "원활")
    private String idx;

    @Schema(description = "구간 경로 좌표 리스트 (|로 구분된 경도_위도 형식)", example = "126.9793_37.5701|126.9810_37.5702")
    private String xyList;
  }
}
