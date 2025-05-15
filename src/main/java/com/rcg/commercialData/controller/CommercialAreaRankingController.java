package com.rcg.commercialData.controller;

import com.rcg.commercialData.dto.TopFloatingAreaDto;
import com.rcg.commercialData.service.CommercialTopFloatingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "상권 순위 API", description = "총 유동인구 수 기준 상위 10개 지역을 조회")
@RestController
@RequestMapping("/api/commercial-areas")
@RequiredArgsConstructor
public class CommercialAreaRankingController {

    private final CommercialTopFloatingService topFloatingService;

    @Operation(summary = "유동인구 상위 10위 지역 조회", description = "116개 지역 중 총 유동인구 수 기준 상위 10개 지역을 반환합니다.")
    @GetMapping("/top-floating")
    public ResponseEntity<List<TopFloatingAreaDto>> getTop10FloatingAreas(
            @RequestParam(required = false, defaultValue = "20244") String stdrYyquCd
    ) {
        return ResponseEntity.ok(topFloatingService.getTop10FloatingAreas(stdrYyquCd));
    }
}
