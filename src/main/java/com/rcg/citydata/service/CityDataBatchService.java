package com.rcg.citydata.service;

import com.rcg.citydata.dto.CityDataDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CityDataBatchService {

  private final Path rootDir;
  private final ObjectMapper mapper;

  public CityDataBatchService(
      @Value("${citydata.path}") String dataPath,
      ObjectMapper objectMapper
  ) {
    this.rootDir = Paths.get(dataPath);
    this.mapper = objectMapper;
  }

  public List<CityDataDto> loadAllAreaMetadata() throws IOException {
    // 1) JSON 파일 목록
    List<Path> jsonFiles;
    try (var stream = Files.walk(rootDir)) {
      jsonFiles = stream
          .filter(Files::isRegularFile)
          .filter(p -> p.toString().endsWith(".json"))
          .collect(Collectors.toList());
    }

    // 2) 트리 모델로 파싱 + DTO 채우기
    List<CityDataDto> result = new ArrayList<>();
    for (Path jsonPath : jsonFiles) {
      JsonNode cityData = mapper.readTree(jsonPath.toFile())
          .path("CITYDATA");

      CityDataDto dto = new CityDataDto();
      // 1) 기본 메타
      dto.setAreaNm(cityData.path("AREA_NM").asText(null));
      dto.setAreaCd(cityData.path("AREA_CD").asText(null));

      // 2) 실시간 인구(첫 배열 요소)
      JsonNode live0 = cityData.path("LIVE_PPLTN_STTS").path(0);
      dto.setAreaCongestLvl(live0.path("AREA_CONGEST_LVL").asText(null));
      dto.setAreaCongestMsg(live0.path("AREA_CONGEST_MSG").asText(null));
      dto.setMalePpltnRate(live0.path("MALE_PPLTN_RATE").asText(null));
      dto.setFemalePpltnRate(live0.path("FEMALE_PPLTN_RATE").asText(null));
      dto.setPpltnRate0(live0.path("PPLTN_RATE_0").asText(null));
      dto.setResntPpltnRate(live0.path("RESNT_PPLTN_RATE").asText(null));
      dto.setNonResntPpltnRate(live0.path("NON_RESNT_PPLTN_RATE").asText(null));

      // 5) 지하철역(첫 요소 + 첫 디테일)
      JsonNode sub0 = cityData.path("SUB_STTS").path(0);
      dto.setSubStnNm(sub0.path("SUB_STN_NM").asText(null));
      dto.setSubStnLine(sub0.path("SUB_STN_LINE").asText(null));
      dto.setSubStnRaddr(sub0.path("SUB_STN_RADDR").asText(null));
      dto.setSubStnJibun(sub0.path("SUB_STN_JIBUN").asText(null));
      dto.setSubStnX(sub0.path("SUB_STN_X").asText(null));
      dto.setSubStnY(sub0.path("SUB_STN_Y").asText(null));
      JsonNode sd0 = sub0.path("SUB_DETAIL").path(0);
      dto.setSubNtStn(sd0.path("SUB_NT_STN").asText(null));
      dto.setSubBfStn(sd0.path("SUB_BF_STN").asText(null));
      dto.setSubRouteNm(sd0.path("SUB_ROUTE_NM").asText(null));
      dto.setSubLine(sd0.path("SUB_LINE").asText(null));

      // 6) 버스정류소(첫 요소 + 첫 버스 디테일)
      JsonNode bus0 = cityData.path("BUS_STN_STTS").path(0);
      dto.setBusStnId(bus0.path("BUS_STN_ID").asText(null));
      dto.setBusArsId(bus0.path("BUS_ARS_ID").asText(null));
      dto.setBusStnNm(bus0.path("BUS_STN_NM").asText(null));
      dto.setBusStnX(bus0.path("BUS_STN_X").asText(null));
      dto.setBusStnY(bus0.path("BUS_STN_Y").asText(null));
      JsonNode bd0 = bus0.path("BUS_DETAIL").path(0);
      dto.setRteStnNm(bd0.path("RTE_STN_NM").asText(null));
      dto.setRteNm(bd0.path("RTE_NM").asText(null));
      dto.setRteId(bd0.path("RTE_ID").asText(null));
      dto.setRteSect(bd0.path("RTE_SECT").asText(null));
      String congest1 = bd0.path("RTE_CONGEST_1").asText("");
      dto.setRteCongest(!congest1.isEmpty()
          ? congest1
          : bd0.path("RTE_CONGEST_2").asText(null));

      // 7) 상권 메타 (CMRCL_RSB 배열 첫 번째 요소) - 추가함
      JsonNode cmc = cityData.path("LIVE_CMRCL_STTS");
      JsonNode rsb0 = cmc.path("CMRCL_RSB").path(0);
      dto.setRsbLrgCtgr(rsb0.path("RSB_LRG_CTGR").asText(null));
      dto.setRsbMidCtgr(rsb0.path("RSB_MID_CTGR").asText(null));
      dto.setRsbMctCnt(rsb0.path("RSB_MCT_CNT").asText(null));
      dto.setRsbMctTime(rsb0.path("RSB_MCT_TIME").asText(null));

      // 8) 연령별 소비 비율 (단일 객체) - 추가함
      dto.setCmrclMaleRate(cmc.path("CMRCL_MALE_RATE").asText(null));
      dto.setCmrclFemaleRate(cmc.path("CMRCL_FEMALE_RATE").asText(null));
      dto.setCmrcl10Rate(cmc.path("CMRCL_10_RATE").asText(null));
      dto.setCmrcl20Rate(cmc.path("CMRCL_20_RATE").asText(null));
      dto.setCmrcl30Rate(cmc.path("CMRCL_30_RATE").asText(null));
      dto.setCmrcl40Rate(cmc.path("CMRCL_40_RATE").asText(null));
      dto.setCmrcl50Rate(cmc.path("CMRCL_50_RATE").asText(null));
      dto.setCmrcl60Rate(cmc.path("CMRCL_60_RATE").asText(null));
      dto.setCmrclPersonalRate(cmc.path("CMRCL_PERSONAL_RATE").asText(null));
      dto.setCmrclCorporationRate(cmc.path("CMRCL_CORPORATION_RATE").asText(null));

      result.add(dto);
    }
    return result;
  }

  public CityDataDto loadByAreaCd(String areaCd) throws IOException {
    return loadAllAreaMetadata().stream()
        .filter(dto -> areaCd.equals(dto.getAreaCd()))
        .findFirst()
        .orElseThrow(() -> new NoSuchElementException(
            "지역 코드 '" + areaCd + "'를 찾을 수 없습니다."
        ));
  }
}
