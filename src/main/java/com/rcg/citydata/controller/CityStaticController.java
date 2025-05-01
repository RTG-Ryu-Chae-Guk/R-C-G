package com.rcg.citydata.controller;

import com.rcg.citydata.dto.CityStaticDto;
import com.rcg.citydata.service.CityStaticService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/static")
@RequiredArgsConstructor
@Tag(name = "CityStatic", description = "도시데이터 비실시간 API")
public class CityStaticController {

  private final CityStaticService service;

  /** 전체 지역 리스트 조회 */
  @GetMapping
  @Operation(
      summary = "전체 지역 목록 조회",
      description = "서울시의 모든 지역에 대한 정적 데이터 목록을 반환합니다."
  )
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json"))
  })

  public List<CityStaticDto> getAll() {
    return service.findAll();
  }

  /** 특정 지역 코드로 조회 */
  @GetMapping("/code/{areaCd}")
  @Operation(
      summary = "지역 코드로 정적 데이터 조회",
      description = "지역 고유 코드(areaCd)를 통해 해당 지역의 정적 데이터를 조회합니다.",
      parameters = {
          @Parameter(name = "areaCd", description = "지역 코드 (예: POI001)", required = true, example = "POI001")
      }
  )
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "404", description = "해당 코드에 대한 지역 데이터 없음")
  })

  public CityStaticDto getByAreaCd(@PathVariable String areaCd) {
    return service.findByAreaCd(areaCd);
  }

  /** 특정 지역 이름으로 조회 */
  @GetMapping("/name/{areaNm}")
  @Operation(
      summary = "지역 이름으로 정적 데이터 조회",
      description = "지역 이름(areaNm)을 통해 해당 지역의 정적 데이터를 조회합니다.",
      parameters = {
          @Parameter(name = "areaNm", description = "지역 이름 (예: 강남 MICE 관광특구)", required = true, example = "강남 MICE 관광특구")
      }
  )
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "404", description = "해당 이름에 대한 지역 데이터 없음")
  })

  public CityStaticDto getByAreaNm(@PathVariable String areaNm) {
    return service.findByAreaNm(areaNm);
  }
}