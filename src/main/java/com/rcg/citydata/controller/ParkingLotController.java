package com.rcg.citydata.controller;

import com.rcg.citydata.dto.CityDynamicDto.ParkingLotStts;
import com.rcg.citydata.service.ParkingLotService;
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
@RequestMapping("/api/parking-lot")
@RequiredArgsConstructor
@Tag(name = "Parking Lot", description = "주차장 실시간 정보 API")
public class ParkingLotController {

    private final ParkingLotService parkingLotService;

    @GetMapping
    @Operation(
            summary = "전체 주차장 목록 조회",
            description = "DB에 저장된 모든 주차장의 실시간 정보를 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json"))
    })
    public List<ParkingLotStts> getAllParkingLots() {
        return parkingLotService.findAllParkingLots();
    }

    @GetMapping("/{prkCd}")
    @Operation(
            summary = "주차장 코드로 조회",
            description = "주차장 고유코드(prkCd)를 기준으로 해당 주차장의 실시간 정보를 조회합니다.",
            parameters = {
                    @Parameter(name = "prkCd", description = "주차장 코드 (예: 1584732)", required = true, example = "1584732")
            }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "해당 코드에 대한 주차장 정보 없음")
    })
    public ParkingLotStts getParkingLotByCode(@PathVariable String prkCd) {
        return parkingLotService.findByPrkCd(prkCd);
    }
}