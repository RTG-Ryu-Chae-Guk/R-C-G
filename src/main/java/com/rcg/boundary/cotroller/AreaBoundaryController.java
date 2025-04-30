package com.rcg.boundary.cotroller;


import com.rcg.boundary.dto.AreaBoundaryDTO;
import com.rcg.boundary.service.AreaBoundaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/boundary")
@RequiredArgsConstructor
@Tag(name = "지역 경계 API", description = "서울시 주요 지역의 GeoJSON 경계 정보를 반환합니다.")
public class AreaBoundaryController {

    private final AreaBoundaryService areaBoundaryService;

    @GetMapping("/{areaName}")
    @Operation(
            summary = "지역 이름으로 경계 조회",
            description = "입력한 지역 이름에 해당하는 경계 좌표를 GeoJSON 스타일로 반환합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "경계 데이터 조회 성공",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AreaBoundaryDTO.class),
                                    examples = @ExampleObject(
                                            value = "{\n  \"name\": \"강남 MICE 관광특구\",\n  \"coordinates\": [[37.5139, 127.0602], [37.5138, 127.0603], ...]\n}"
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "해당 지역을 찾을 수 없음")
            }
    )
    public ResponseEntity<Map<String, Object>> getAreaBoundary(@PathVariable String areaName) {
        AreaBoundaryDTO result = areaBoundaryService.getAreaBoundaryByName(areaName);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }

        // GeoJSON 형식으로 포장
        Map<String, Object> geoJson = new HashMap<>();
        geoJson.put("type", "Feature");

        Map<String, Object> geometry = new HashMap<>();
        geometry.put("type", "Polygon");
        geometry.put("coordinates", List.of(result.getCoordinates()));

        geoJson.put("geometry", geometry);

        Map<String, Object> properties = new HashMap<>();
        properties.put("name", result.getName());

        geoJson.put("properties", properties);

        return ResponseEntity.ok(geoJson);
    }
}
