// CityDynamicLoader.java
package com.rcg.citydata.loader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rcg.citydata.entity.CityDynamic;
import com.rcg.citydata.entity.CityDynamicId;
import com.rcg.citydata.repository.CityDynamicRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class CityDynamicLoader implements CommandLineRunner {

  private final CityDynamicRepository dynamicRepository;
  private final ObjectMapper mapper;

  @Value("${citydata.path}")
  private String dataPath;

  private static final Set<String> staticFields = Set.of(
      "AREA_CONGEST_LVL","AREA_CONGEST_MSG",
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
      "CMRCL_CORPORATION_RATE",
      // 제외하려는 것들 필드명
      "REPLACE","ROAD_TRAFFIC_STTS","ROAD_TRAFFIC_SPD",
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
      "THUMBNAIL","URL","EVENT_ETC_DETAIL",     "EVENT_STTS"
//      "PPLTN_RATE_0"
//      "AREA_NM","AREA_CD"
      );

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    if (dynamicRepository.count() > 0) {
      System.out.println("✅ CityDynamic data already loaded, skip");
      return;
    }

    try (Stream<Path> paths = Files.walk(Paths.get(dataPath))) {
      List<CityDynamic> list = paths
          .filter(p -> p.toString().endsWith(".json"))
          .map(path -> {
            try {
              JsonNode city = mapper.readTree(path.toFile())
                  .path("CITYDATA");
              ObjectNode dynNode = city.deepCopy();
              staticFields.forEach(dynNode::remove);

              CityDynamic cd = new CityDynamic();
              CityDynamicId id = new CityDynamicId();
              id.setAreaCd(city.path("AREA_CD").asText());
              id.setAreaNm(city.path("AREA_NM").asText());
              id.setMeasuredAt(LocalDateTime.now());
              cd.setId(id);
              cd.setDynamicData(mapper.writeValueAsString(dynNode));
              return cd;
            } catch (Exception ex) {
              throw new RuntimeException("Failed to load dynamic from " + path, ex);
            }
          })
          .collect(Collectors.toList());

      dynamicRepository.saveAll(list);
      System.out.println("✅ Loaded CityDynamic records: " + list.size());
    }
  }
}
