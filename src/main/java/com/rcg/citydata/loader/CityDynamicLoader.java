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

  // 엑셀 기준 포함하고 싶은 필드 목록
  private static final Set<String> wantedFields = Set.of(
          "AREA_NM", "AREA_CD", "LIVE_PPLTN_STTS", "AREA_CONGEST_LVL", "AREA_CONGEST_MSG",
          "AREA_PPLTN_MIN", "AREA_PPLTN_MAX", "MALE_PPLTN_RATE", "FEMALE_PPLTN_RATE",
          "PPLTN_RATE_0", "PPLTN_RATE_10", "PPLTN_RATE_20", "PPLTN_RATE_30", "PPLTN_RATE_40",
          "PPLTN_RATE_50", "PPLTN_RATE_60", "PPLTN_RATE_70", "RESNT_PPLTN_RATE", "NON_RESNT_PPLTN_RATE",
          "PPLTN_TIME", "FCST_YN", "FCST_PPLTN", "FCST_TIME", "FCST_CONGEST_LVL",
          "FCST_PPLTN_MIN", "FCST_PPLTN_MAX", "ROAD_ADDR", "ADDRESS", "LAT", "LNG",
          "SUB_STN_NM", "SUB_STN_LINE", "SUB_ROUTE_NM", "SUB_LINE",
          "BUS_STN_STTS", "BUS_RESULT_MSG", "BUS_STN_NM", "RTE_STN_NM", "RTE_NM",
          "RTE_ID", "RTE_SECT", "RTE_CONGEST", "LIVE_CMRCL_STTS", "AREA_CMRCL_LVL",
          "AREA_SH_PAYMENT_CNT", "AREA_SH_PAYMENT_AMT_MIN", "AREA_SH_PAYMENT_AMT_MAX",
          "RSB_LRG_CTGR", "RSB_MID_CTGR", "RSB_PAYMENT_LVL", "RSB_SH_PAYMENT_CNT",
          "RSB_SH_PAYMENT_AMT_MIN", "RSB_SH_PAYMENT_AMT_MAX", "RSB_MCT_CNT", "RSB_MCT_TIME",
          "CMRCL_MALE_RATE", "CMRCL_FEMALE_RATE", "CMRCL_10_RATE", "CMRCL_20_RATE",
          "CMRCL_30_RATE", "CMRCL_40_RATE", "CMRCL_50_RATE", "CMRCL_60_RATE",
          "CMRCL_PERSONAL_RATE", "CMRCL_CORPORATION_RATE", "CMRCL_TIME"
  );

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    if (dynamicRepository.count() > 0) {
      System.out.println("\u2705 CityDynamic data already loaded, skip");
      return;
    }

    try (Stream<Path> paths = Files.walk(Paths.get(dataPath))) {
      List<CityDynamic> list = paths
              .filter(p -> p.toString().endsWith(".json"))
              .map(path -> {
                try {
                  JsonNode city = mapper.readTree(path.toFile()).path("CITYDATA");
                  ObjectNode dynNode = mapper.createObjectNode();

                  // 필요한 필드만 추출해서 저장
                  wantedFields.forEach(field -> {
                    if (city.has(field)) {
                      dynNode.set(field, city.get(field));
                    }
                  });

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
      System.out.println("\u2705 Loaded CityDynamic records: " + list.size());
    }
  }
}
