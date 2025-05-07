package com.rcg.commercialData.controller;

import com.rcg.commercialData.dto.*;
import com.rcg.commercialData.service.CommercialAreaDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "상권 상세 API", description = "상권 기본 정보, 유동인구, 매출, 점포현황, 상주인구, 소비지표 등 개별 조회 API")
@RestController
@RequestMapping("/api/commercial-areas")
@RequiredArgsConstructor
@Slf4j
public class CommercialAreaDetailController {

    private final CommercialAreaDetailService detailService;

    @Operation(summary = "상권 기본 정보 조회", description = "상권의 이름, 위치 등 기본 정보를 조회합니다.")
    @GetMapping("/{trdarCd}")
    public ResponseEntity<CommercialAreaDto> getArea(
            @Parameter(name = "trdarCd", description = "상권 코드", example = "3001491")
            @PathVariable(required = false) String trdarCd
    ) {
        return ResponseEntity.ok(detailService.getArea(trdarCd));
    }

    @Operation(summary = "유동인구 조회", description = "특정 분기의 유동인구 정보를 조회합니다.")
    @GetMapping("/{trdarCd}/floating")
    public ResponseEntity<CommercialAreaFloatingPopulationDto> getFloating(
            @Parameter(name = "trdarCd", description = "상권 코드", example = "3001491")
            @PathVariable String trdarCd,
            @Parameter(name = "stdrYyquCd", description = "기준 년도-분기 코드", example = "20244")
            @RequestParam(required = false) String stdrYyquCd
    ) {
        return ResponseEntity.ok(detailService.getFloatingPopulation(trdarCd, stdrYyquCd));
    }

    @Operation(summary = "매출 정보 조회", description = "특정 분기의 업종별 매출 데이터를 조회합니다.")
    @GetMapping("/{trdarCd}/sales")
    public ResponseEntity<List<CommercialAreaSalesDto>> getSales(
            @Parameter(name = "trdarCd", description = "상권 코드", example = "3001491")
            @PathVariable String trdarCd,
            @Parameter(name = "stdrYyquCd", description = "기준 년도-분기 코드", example = "20244")
            @RequestParam(required = false) String stdrYyquCd
    ) {
        return ResponseEntity.ok(detailService.getSales(trdarCd, stdrYyquCd));
    }

    @Operation(summary = "점포 현황 조회", description = "특정 분기의 점포 수 및 증감 데이터를 조회합니다.")
    @GetMapping("/{trdarCd}/stores")
    public ResponseEntity<List<CommercialAreaStoreStatusDto>> getStores(
            @Parameter(name = "trdarCd", description = "상권 코드", example = "3001491")
            @PathVariable String trdarCd,
            @Parameter(name = "stdrYyquCd", description = "기준 년도-분기 코드", example = "20244")
            @RequestParam(required = false) String stdrYyquCd
    ) {
        return ResponseEntity.ok(detailService.getStoreStatus(trdarCd, stdrYyquCd));
    }

    @Operation(summary = "상주인구 조회", description = "특정 분기의 상주 인구 데이터를 조회합니다.")
    @GetMapping("/{trdarCd}/residents")
    public ResponseEntity<CommercialResidentPopulationDto> getResidents(
            @Parameter(name = "trdarCd", description = "상권 코드", example = "3001491")
            @PathVariable String trdarCd,
            @Parameter(name = "stdrYyquCd", description = "기준 년도-분기 코드", example = "20244")
            @RequestParam(required = false) String stdrYyquCd
    ) {
        return ResponseEntity.ok(detailService.getResidentPopulation(trdarCd, stdrYyquCd));
    }

    @Operation(summary = "소비 지출 정보 조회", description = "특정 분기의 소비자 지출 데이터를 조회합니다.")
    @GetMapping("/{trdarCd}/spending")
    public ResponseEntity<CommercialSpendingDto> getSpending(
            @Parameter(name = "trdarCd", description = "상권 코드", example = "3001491")
            @PathVariable String trdarCd,
            @Parameter(name = "stdrYyquCd", description = "기준 년도-분기 코드", example = "20244")
            @RequestParam(required = false) String stdrYyquCd
    ) {
        return ResponseEntity.ok(detailService.getSpending(trdarCd, stdrYyquCd));
    }
}
