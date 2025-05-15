package com.rcg.citydata.scheduler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rcg.citydata.entity.CityDynamic;
import com.rcg.citydata.entity.CityDynamicId;
import com.rcg.citydata.repository.CityDynamicRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.FileInputStream;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
@RequiredArgsConstructor
public class CityDynamicScheduler {

  private final CityDynamicRepository dynamicRepository;
  private final ObjectMapper mapper;
  private final WebClient webClient = WebClient.builder()
          .exchangeStrategies(ExchangeStrategies.builder()
                  .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(100 * 1024 * 1024))
                  .build())
          .build();

  @Value("${citydata.api-key}")
  private String apiKey;

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
          "CMRCL_PERSONAL_RATE", "CMRCL_CORPORATION_RATE", "CMRCL_TIME",
          "PRK_STTS", "PRK_NM", "PRK_CD", "PRK_TYPE", "CPCTY",
          "CUR_PRK_CNT", "CUR_PRK_TIME", "CUR_PRK_YN",
          "PAY_YN", "RATES", "TIME_RATES", "ADD_RATES", "ADD_TIME_RATES",
          "ROAD_MSG"
  );

  private static final String EXCEL_PATH = "src/main/resources/data/Seoul_place.xlsx";

  // 서버 실행시 자동 실행
  @PostConstruct
  public void onStartupLoadOnce() {
    loadAndSaveDynamicData();
  }

  // 매 시각 정각 (00분 00초)에 실행
  @Scheduled(cron = "0 0 * * * ?", zone = "Asia/Seoul")
  @Transactional
  public void loadAndSaveDynamicData() {
    String apiUrlTemplate = "http://openapi.seoul.go.kr:8088/{apiKey}/json/citydata/1/5/{areaName}";

    // 시 단위로 timestamp 고정
    LocalDateTime timestamp = LocalDateTime.now()
            .withMinute(0).withSecond(0).withNano(0);

    try {
      List<Map.Entry<String, String>> regionList = loadRegionList();

      for (Map.Entry<String, String> region : regionList) {
        String areaCd = region.getKey();
        String areaNm = region.getValue();

        try {
          String jsonResponse = webClient.get()
                  .uri(apiUrlTemplate, apiKey, areaNm)
                  .retrieve()
                  .bodyToMono(String.class)
                  .block();

          ObjectNode rootNode = (ObjectNode) mapper.readTree(jsonResponse).path("CITYDATA");
          ObjectNode dynamicNode = mapper.createObjectNode();
          wantedFields.forEach(field -> {
            if (rootNode.has(field)) {
              dynamicNode.set(field, rootNode.get(field));
            }
          });

          CityDynamicId id = new CityDynamicId(
                  rootNode.path("AREA_CD").asText(),
                  rootNode.path("AREA_NM").asText(),
                  timestamp
          );

          // 기존 데이터 삭제
          dynamicRepository.deleteByIdAreaNm(id.getAreaNm());

          
          CityDynamic cityDynamic = new CityDynamic();
          cityDynamic.setId(id);
          cityDynamic.setDynamicData(dynamicNode.toString());
          dynamicRepository.save(cityDynamic);

          System.out.println("✅ 저장 완료: " + areaNm + " / " + timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        } catch (Exception e) {
          System.err.println("❌ 실패: " + areaNm + " / 이유: " + e.getMessage());
        }
      }
    } catch (Exception e) {
      System.err.println("전체 처리 실패: " + e.getMessage());
    }
  }

  private List<Map.Entry<String, String>> loadRegionList() {
    List<Map.Entry<String, String>> result = new ArrayList<>();
    try (Workbook workbook = new XSSFWorkbook(new FileInputStream(Paths.get(EXCEL_PATH).toFile()))) {
      Sheet sheet = workbook.getSheetAt(0);
      boolean isFirst = true;

      for (Row row : sheet) {
        if (isFirst) {
          isFirst = false;
          continue; // header skip
        }
        Cell codeCell = row.getCell(2); // AREA_CD
        Cell nameCell = row.getCell(3); // AREA_NM
        if (codeCell != null && nameCell != null) {
          result.add(Map.entry(codeCell.getStringCellValue(), nameCell.getStringCellValue()));
        }
      }
    } catch (Exception e) {
      System.err.println("엑셀 읽기 실패: " + e.getMessage());
    }
    return result;
  }
}


