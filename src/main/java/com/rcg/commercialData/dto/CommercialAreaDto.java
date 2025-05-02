package com.rcg.commercialData.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "상권 요약 정보 DTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommercialAreaDto {

    @Schema(description = "상권 코드", example = "A0001")
    private String trdarCd;

    @Schema(description = "상권 이름", example = "강남역 상권")
    private String trdarCdNm;

/*    @Schema(description = "경도", example = "127.0276")
    private Double xcntsValue;

    @Schema(description = "위도", example = "37.4979")
    private Double ydntsValue;*/

    @Schema(description = "자치구 이름", example = "강남구")
    private String signguCdNm;

    @Schema(description = "행정동 이름", example = "역삼동")
    private String adstrdCdNm;

    @Schema(description = "상권 면적 (m²)", example = "1234.5")
    private Double relmAr;

    @Schema(description = "위도 (WGS84)", example = "37.4979")
    private Double latitude;

    @Schema(description = "경도 (WGS84)", example = "127.0276")
    private Double longitude;
}


