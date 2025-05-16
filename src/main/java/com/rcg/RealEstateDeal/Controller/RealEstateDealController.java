package com.rcg.RealEstateDeal.Controller;


import com.rcg.RealEstateDeal.Entity.RealEstateDeal;
import com.rcg.RealEstateDeal.Service.RealEstateDealService;
import com.rcg.RealEstateDeal.repository.RealEstateDealRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/realestate")
@RequiredArgsConstructor
@Tag(name = "부동산 거래 API", description = "서울시 상업용 부동산 실거래 데이터를 수집 및 조회하는 API입니다.")
public class RealEstateDealController {

    private final RealEstateDealService dealService;
    private final RealEstateDealRepository dealRepository;

    @Operation(
            summary = "실거래가 저장",
            description = "서울시 25개 자치구의 실거래 데이터를 공공데이터 API에서 조회하고 데이터베이스에 저장합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "저장 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/fetch")
    public ResponseEntity<Void> fetchDeals() {
        dealService.fetchAndSaveDeals();
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "실거래 데이터 전체 조회",
            description = "데이터베이스에 저장된 상업용 부동산 실거래 데이터를 모두 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공")
    })
    @GetMapping
    public ResponseEntity<List<RealEstateDeal>> getAll() {
        return ResponseEntity.ok(dealRepository.findAll());
    }
}