package com.rcg.citydata.scheduler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rcg.citydata.entity.CityDynamic;
import com.rcg.citydata.entity.CityDynamicId;
import com.rcg.citydata.repository.CityDynamicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Stream;

/**
 * 서울시 실시간 도시 데이터 스케줄러
 * 수정: 각 지역별 폴더 하위 JSON 파일 삭제 후 새로 저장 및 DB 초기화
 */
@Component
@RequiredArgsConstructor
public class CityDynamicScheduler {

  private final CityDynamicRepository dynamicRepository;
  private final ObjectMapper mapper;
  private final WebClient webClient =
      WebClient.builder()
          .exchangeStrategies(ExchangeStrategies.builder()
              .codecs(configurer ->
                  configurer.defaultCodecs()
                      .maxInMemorySize(100 * 1024 * 1024)  // 100MB
              )
              .build())
          .build();

  @Value("${citydata.path}")
  private String dataPath;

  // 제외할 JSON 필드 목록
  private static final Set<String> excludeFields = Set.of(
      "AREA_NM","AREA_CD","AREA_CONGEST_LVL","AREA_CONGEST_MSG",
      "MALE_PPLTN_RATE","FEMALE_PPLTN_RATE",
      "RESNT_PPLTN_RATE","NON_RESNT_PPLTN_RATE","SUB_STN_NM",
      "SUB_STN_LINE","SUB_STN_RADDR","SUB_STN_JIBUN","SUB_STN_X",
      "SUB_STN_Y","SUB_NT_STN","SUB_BF_STN","SUB_ROUTE_NM",
      "SUB_LINE","BUS_STN_ID","BUS_ARS_ID","BUS_STN_NM",
      "BUS_STN_X","BUS_STN_Y","RTE_STN_NM","RTE_NM","RTE_ID",
      "RTE_SECT","RTE_CONGEST","RSB_LRG_CTGR","RSB_MID_CTGR",
      "RSB_MCT_CNT","RSB_MCT_TIME","CMRCL_MALE_RATE","CMRCL_FEMALE_RATE",
      "CMRCL_10_RATE","CMRCL_20_RATE","CMRCL_30_RATE","CMRCL_40_RATE",
      "CMRCL_50_RATE","CMRCL_60_RATE","CMRCL_PERSONAL_RATE",
      "CMRCL_CORPORATION_RATE","REPLACE","ROAD_TRAFFIC_STTS","ROAD_TRAFFIC_SPD",
      "ROAD_TRAFFIC_IDX","ROAD_TRAFFIC_TIME","ROAD_MSG","LINK_ID",
      "ROAD_NM","START_ND_CD","START_ND_NM","START_ND_XY","END_ND_CD",
      "END_ND_NM","END_ND_XY","DIST","SPD","IDX","XYLIST","PRK_STTS",
      "PRK_NM","PRK_CD","PRK_TYPE","CPCTY","CUR_PRK_CNT","CUR_PRK_TIME","CUR_PRK_YN",
      "RATES","TIME_RATES","ADD_RATES","ADD_TIME_RATES","SUB_STTS",
      "SUB_ORD","SUB_DIR","SUB_TERMINAL","SUB_ARVTIME","SUB_ARMG1","SUB_ARMG2","SUB_ARVINFO",
      "SUB_FACINFO","ELVTR_NM","OPR_SEC","INSTL_PSTN","USE_YN","ELVTR_SE",
      "RTE_ARRV_STN","ACDNT_CNTRL_STTS","ACDNT_OCCR_DT","EXP_CLR_DT","ACDNT_TYPE","ACDNT_DTYPE",
      "ACDNT_INFO","ACDNT_X","ACDNT_Y","ACDNT_TIME","CHARGER_STTS","STAT_NM","STAT_ID","STAT_ADDR",
      "STAT_X","STAT_Y","STAT_USETIME","STAT_PARKPAY","STAT_LIMITYN","STAT_LIMITDETAIL","STAT_KINDDETAIL",
      "CHARGER_ID","CHARGER_TYPE","CHARGER_STAT","STATUPDDT","LASTTSDT","LASTTEDT","NOWTSDT","OUTPUT","METHOD",
      "SBIKE_STTS","SBIKE_SPOT_NM","SBIKE_SPOT_ID","SBIKE_SHARED","SBIKE_PARKING_CNT","SBIKE_RACK_CNT","SBIKE_X","SBIKE_Y",
      "WEATHER_STTS","TEMP","SENSIBLE_TEMP","MAX_TEMP","MIN_TEMP","HUMIDITY","WIND_DIRCT","WIND_SPD",
      "PRECIPITATION","PRECPT_TYPE","PCP_MSG","SUNRISE","SUNSET","UV_INDEX_LVL","UV_INDEX","UV_MSG",
      "PM25_INDEX","PM25","PM10_INDEX","PM10","AIR_IDX","AIR_IDX_MVL","AIR_IDX_MAIN","AIR_MSG",
      "WEATHER_TIME","NEWS_LIST","WARN_VAL","WARN_STRESS","ANNOUNCE_TIME",
      "COMMAND","CANCEL_YN","WARN_MSG","FCST24HOURS",
      "FCST_DT","RAIN_CHANCE","SKY_STTS",
      "CULTURALEVENTINFO","EVENT_NM","EVENT_PERIOD","EVENT_PLACE","EVENT_X","EVENT_Y",
      "THUMBNAIL","URL","EVENT_ETC_DETAIL","EVENT_STTS"
  );

  /**
   * 매시 13분마다 각 지역 폴더 이름의 접두사(지역코드)를 이용해 API 호출 후,
   * 해당 폴더 하위 모든 JSON 파일을 삭제하고 새 JSON을 저장, DB도 비우고 다시 기록합니다.
   */
  @Scheduled(cron = "0 57 * * * ?",zone = "Asia/Seoul")
  @Transactional
  public void loadAndSaveDynamicData() {
    // 기존 DB 데이터 일괄 삭제
    dynamicRepository.deleteAllInBatch();

    String apiUrl = "http://openapi.seoul.go.kr:8088/4d716b496772696e37397855435551/json/citydata/1/5/{regionCode}";
    Path baseDir = Paths.get(dataPath);

    try (Stream<Path> folders = Files.list(baseDir).filter(Files::isDirectory)) {
      folders.forEach(folder -> {
        String folderName = folder.getFileName().toString();
        String regionCode = folderName.contains("_")
            ? folderName.substring(0, folderName.indexOf('_'))
            : folderName;

        try {
          // 폴더 내 기존 JSON 파일 삭제
          try (Stream<Path> oldFiles = Files.list(folder).filter(p -> p.toString().endsWith(".json"))) {
            oldFiles.forEach(p -> {
              try {
                Files.deleteIfExists(p);
              } catch (Exception ignore) {}
            });
          } catch (Exception ignore) {}

          // API 호출
          String jsonResponse = webClient.get()
              .uri(apiUrl, regionCode)
              .retrieve()
              .bodyToMono(String.class)
              .block();

          // 파일명 생성 및 저장
          String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
          String filename = String.format("citydata_%s_%s.json", regionCode, timestamp);
          Path filePath = folder.resolve(filename);
          Files.createDirectories(folder);
          Files.writeString(filePath, jsonResponse);

          // JSON 파싱 및 DB 저장
          ObjectNode rootNode = (ObjectNode) mapper.readTree(jsonResponse).path("CITYDATA");
          ObjectNode dynamicNode = rootNode.deepCopy();
          excludeFields.forEach(dynamicNode::remove);

          CityDynamic cityDynamic = new CityDynamic();
          CityDynamicId id = new CityDynamicId(
              rootNode.path("AREA_CD").asText(),
              rootNode.path("AREA_NM").asText(),
              LocalDateTime.now()
          );
          cityDynamic.setId(id);
          cityDynamic.setDynamicData(dynamicNode.toString());
          dynamicRepository.save(cityDynamic);

          System.out.println("✅ [" + folderName + "] 저장 완료: " + filePath);
        } catch (Exception ex) {
          System.err.println("❌ [" + folderName + "] 처리 실패: " + ex.getMessage());
        }
      });
    } catch (Exception e) {
      System.err.println("폴더 스캔 실패: " + e.getMessage());
    }
  }
}
