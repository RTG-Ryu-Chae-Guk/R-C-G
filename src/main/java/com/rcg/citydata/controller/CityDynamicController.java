package com.rcg.citydata.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rcg.citydata.dto.CityDynamicDto;
import com.rcg.citydata.entity.CityDynamic;
import com.rcg.citydata.repository.CityDynamicRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dynamic")
@RequiredArgsConstructor
@Tag(name = "CityDynamic", description = "도시데이터 실시간 API")
public class CityDynamicController {
  private final CityDynamicRepository cityDynamicRepository;
  private final ObjectMapper mapper;

  @GetMapping("/citydata")
  @Operation(
      summary = "지역 이름으로 실시간 도시데이터 조회",
      description = "사용자가 입력한 지역 이름(areaNm)을 기준으로 최신 실시간 도시데이터를 조회합니다.",
      parameters = {
          @Parameter(
              name = "areaNm",
              description = "조회할 지역 이름 (예: 강남 MICE 관광특구)",
              required = true,
              example = "강남 MICE 관광특구"
          )
      },
      responses = {
          @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "404", description = "해당 지역 데이터가 없음"),
          @ApiResponse(responseCode = "500", description = "JSON 매핑 실패 또는 서버 내부 오류")
      }
  )

  public ResponseEntity<CityDynamicDto> getCityData(@RequestParam("areaNm") String areaNm) {
    // areaNm 으로 저장된 레코드 중 최신 순으로 정렬해서 첫 건을 꺼냄
    List<CityDynamic> list = cityDynamicRepository.findByIdAreaNmOrderByIdMeasuredAtDesc(areaNm);
    if (list.isEmpty()) {
      throw new EntityNotFoundException("존재하지 않는 지역입니다: " + areaNm);
    }
    CityDynamic latest = list.get(0);

    // DB 에 저장된 dynamicData(json) -> DTO 로 매핑
    CityDynamicDto dto;
    try {
      dto = mapper.readValue(latest.getDynamicData(), CityDynamicDto.class);
    } catch (Exception e) {
      throw new RuntimeException("JSON 매핑 오류: " + e.getMessage(), e);
    }
    return ResponseEntity.ok(dto);
  }
}
