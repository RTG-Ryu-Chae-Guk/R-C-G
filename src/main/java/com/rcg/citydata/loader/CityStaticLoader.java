package com.rcg.citydata.loader;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rcg.citydata.repository.CityStaticRepository;
import com.rcg.citydata.entity.CityStatic;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * CITYDATA > STATIC 데이터를 로드하며,
 * 실시간(LIVE_) 과 비실시간(CMRCL_RSB, PPLTN, RSB) 배열 또는 객체를 모두 병합합니다.
 */
@Component
@RequiredArgsConstructor
public class CityStaticLoader implements CommandLineRunner {

  private final CityStaticRepository staticRepository;
  private final ObjectMapper mapper;

  @Value("${citydata.path}")
  private String dataPath;

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    // JSON 필드명을 Snake_Case로 읽되, 객체 레벨 @JsonProperty 우선
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

    if (staticRepository.count() > 0) {
      System.out.println("✅ CityStatic data already loaded, skip");
      return;
    }

    try (Stream<Path> paths = Files.walk(Paths.get(dataPath))) {
      List<CityStatic> list = paths
          .filter(p -> p.toString().endsWith(".json"))
          .map(path -> {
            try {
              ObjectNode node = (ObjectNode) mapper.readTree(path.toFile()).path("CITYDATA");

              // LIVE 인구
              mergeObjectOrArray(node, "LIVE_PPLTN_STTS");
              // PPLTN (비실시간 인구 분포)
              mergeObjectOrArray(node, "PPLTN_STTS");

              // LIVE 상권
              mergeObjectOrArray(node, "LIVE_CMRCL_STTS");
              // RSB (비실시간 업종 정보) — 실제 JSON 키: CMRCL_RSB
              mergeObjectOrArray(node, "CMRCL_RSB");

              // LIVE 지하철
              mergeObjectOrArray(node, "SUB_STTS");
              mergeObjectOrArray(node, "SUB_DETAIL");
              // LIVE 버스
              mergeObjectOrArray(node, "BUS_STN_STTS");

              // BUS_DETAIL 중 일부만 매핑
              JsonNode busDetail = node.get("BUS_DETAIL");
              if (busDetail != null && busDetail.isArray() && busDetail.size() > 0 && busDetail.get(0).isObject()) {
                ObjectNode bd0 = (ObjectNode) busDetail.get(0);
                node.put("RTE_CONGEST", bd0.path("RTE_CONGEST_1").asText(""));
                copyIfExists(bd0, node, "RTE_STN_NM");
                copyIfExists(bd0, node, "RTE_NM");
                copyIfExists(bd0, node, "RTE_ID");
                copyIfExists(bd0, node, "RTE_SECT");
                node.remove("BUS_DETAIL");
              }

              // 엔티티 매핑 전 로드 시간 설정
              CityStatic cs = mapper.convertValue(node, CityStatic.class);
              if (cs.getAreaCd() == null) {
                throw new IllegalStateException("area_cd 가 null 입니다! 파일: " + path);
              }
              cs.setLoadedAt(LocalDateTime.now());
              return cs;

            } catch (Exception e) {
              throw new RuntimeException("Failed to load static from " + path, e);
            }
          })
          .collect(Collectors.toList());

      staticRepository.saveAll(list);
      System.out.println("✅ Loaded CityStatic records: " + list.size());
    }
  }

  /**
   * key 로 객체 또는 배열이 있으면, 첫번째 요소(Object) 혹은 객체 자체를 꺼내 병합 후 해당 필드를 제거
   */
  private void mergeObjectOrArray(ObjectNode node, String key) {
    JsonNode jn = node.get(key);
    if (jn == null) return;

    if (jn.isArray() && jn.size() > 0 && jn.get(0).isObject()) {
      node.setAll((ObjectNode) jn.get(0));
      node.remove(key);

    } else if (jn.isObject()) {
      node.setAll((ObjectNode) jn);
      node.remove(key);
    }
  }

  /**
   * src 에 key 가 존재하면 dst 에 복사
   */
  private void copyIfExists(ObjectNode src, ObjectNode dst, String key) {
    JsonNode v = src.get(key);
    if (v != null) dst.set(key, v);
  }
}