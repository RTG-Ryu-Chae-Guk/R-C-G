package com.rcg.commercialData.controller;

import com.rcg.commercialData.dto.CommercialAreaDto;
import com.rcg.commercialData.service.CommercialAreaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "상권 구역 API", description = "상권 데이터 관련 API")
@RestController
@RequestMapping("/api/commercial-areas")
@RequiredArgsConstructor
public class CommercialAreaController {

    private final CommercialAreaService commercialAreaService;

    @Operation(summary = "전체 상권 조회", description = "지도에 표시할 전체 상권 목록을 조회합니다.")
    @GetMapping
    public ResponseEntity<List<CommercialAreaDto>> getAllCommercialAreas() {
        List<CommercialAreaDto> areas = commercialAreaService.getAllCommercialAreas();
        return ResponseEntity.ok(areas);
    }

    @Operation(summary = "지도 범위 내 상권 조회", description = "현재 지도 화면 내에 있는 상권만 필터링하여 조회합니다.")
    @GetMapping("/bounding-box")
    public ResponseEntity<List<CommercialAreaDto>> getCommercialAreasInBoundingBox(
            @Parameter(description = "지도에서 남쪽(하단)의 위도 값", example = "37.570") @RequestParam Double minLat,
            @Parameter(description = "지도에서 북쪽(상단)의 위도 값", example = "37.590") @RequestParam Double maxLat,
            @Parameter(description = "지도에서 서쪽(좌측)의 경도 값", example = "126.960") @RequestParam Double minLng,
            @Parameter(description = "지도에서 동쪽(우측)의 경도 값", example = "126.990") @RequestParam Double maxLng) {

        List<CommercialAreaDto> areas = commercialAreaService.getAreasInBoundingBox(minLat, maxLat, minLng, maxLng);
        return ResponseEntity.ok(areas);
    }

}
