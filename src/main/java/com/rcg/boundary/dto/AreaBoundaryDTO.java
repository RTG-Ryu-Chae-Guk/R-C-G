package com.rcg.boundary.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "특정 지역의 경계 정보를 담는 DTO")
public class AreaBoundaryDTO {

    @Schema(description = "지역 이름", example = "강남 MICE 관광특구")
    private String name;

    @Schema(description = "경계 좌표 목록 (GeoJSON 형식의 Polygon 좌표계)",
            example = "[[37.5139, 127.0602], [37.5138, 127.0603], ...]")
    private List<List<Double>> coordinates;
}