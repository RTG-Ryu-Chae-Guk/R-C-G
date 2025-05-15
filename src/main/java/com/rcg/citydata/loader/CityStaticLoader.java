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

@Component
@RequiredArgsConstructor
public class CityStaticLoader implements CommandLineRunner {

  private final CityStaticRepository staticRepository;
  private final ObjectMapper originalMapper;

  @Value("${citydata.path}")
  private String dataPath;

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    ObjectMapper mapper = originalMapper.copy(); // 새로운 복사본으로 안전하게 구성

    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true); // 안전하게 복사본에 적용

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

                  mergeObjectOrArray(node, "LIVE_PPLTN_STTS");
                  mergeObjectOrArray(node, "PPLTN_STTS");
                  mergeObjectOrArray(node, "LIVE_CMRCL_STTS");
                  mergeObjectOrArray(node, "CMRCL_RSB");
                  mergeObjectOrArray(node, "SUB_STTS");
                  mergeObjectOrArray(node, "SUB_DETAIL");
                  mergeObjectOrArray(node, "BUS_STN_STTS");

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

  private void copyIfExists(ObjectNode src, ObjectNode dst, String key) {
    JsonNode v = src.get(key);
    if (v != null) dst.set(key, v);
  }
}
