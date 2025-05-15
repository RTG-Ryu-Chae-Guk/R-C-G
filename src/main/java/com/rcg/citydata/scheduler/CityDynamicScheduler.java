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

  @Value("${citydata.path}")
  private String dataPath;

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
          "CMRCL_PERSONAL_RATE", "CMRCL_CORPORATION_RATE", "CMRCL_TIME"
  );

  @Scheduled(cron = "0 57 * * * ?", zone = "Asia/Seoul")
  @Transactional
  public void loadAndSaveDynamicData() {
    dynamicRepository.deleteAllInBatch();

    String apiUrlTemplate = "http://openapi.seoul.go.kr:8088/{apiKey}/json/citydata/1/5/{regionCode}";
    Path baseDir = Paths.get(dataPath);

    try (Stream<Path> folders = Files.list(baseDir).filter(Files::isDirectory)) {
      folders.forEach(folder -> {
        String folderName = folder.getFileName().toString();
        String regionCode = folderName.contains("_") ? folderName.substring(0, folderName.indexOf('_')) : folderName;

        try {
          // 삭제 기존 JSON
          try (Stream<Path> oldFiles = Files.list(folder).filter(p -> p.toString().endsWith(".json"))) {
            oldFiles.forEach(p -> {
              try { Files.deleteIfExists(p); } catch (Exception ignore) {}
            });
          } catch (Exception ignore) {}

          // API 호출
          String jsonResponse = webClient.get()
                  .uri(apiUrlTemplate, apiKey, regionCode)
                  .retrieve()
                  .bodyToMono(String.class)
                  .block();

          // 파일 저장
          String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
          String filename = String.format("citydata_%s_%s.json", regionCode, timestamp);
          Path filePath = folder.resolve(filename);
          Files.createDirectories(folder);
          Files.writeString(filePath, jsonResponse);

          // JSON 파싱 및 저장
          ObjectNode rootNode = (ObjectNode) mapper.readTree(jsonResponse).path("CITYDATA");
          ObjectNode dynamicNode = mapper.createObjectNode();
          wantedFields.forEach(field -> {
            if (rootNode.has(field)) {
              dynamicNode.set(field, rootNode.get(field));
            }
          });

          CityDynamic cityDynamic = new CityDynamic();
          CityDynamicId id = new CityDynamicId(
                  rootNode.path("AREA_CD").asText(),
                  rootNode.path("AREA_NM").asText(),
                  LocalDateTime.now()
          );
          cityDynamic.setId(id);
          cityDynamic.setDynamicData(dynamicNode.toString());
          dynamicRepository.save(cityDynamic);

          System.out.println("\u2705 [" + folderName + "] 저장 완료: " + filePath);
        } catch (Exception ex) {
          System.err.println("\u274C [" + folderName + "] 처리 실패: " + ex.getMessage());
        }
      });
    } catch (Exception e) {
      System.err.println("폴더 스캔 실패: " + e.getMessage());
    }
  }
}
