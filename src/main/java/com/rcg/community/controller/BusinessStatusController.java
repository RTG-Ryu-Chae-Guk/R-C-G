package com.rcg.community.controller;

import com.rcg.community.dto.BusinessStatusRequestDTO;
import com.rcg.community.dto.BusinessStatusResponseDTO;
import com.rcg.jwt.JwtUtil;
import com.rcg.community.service.BusinessStatusService;
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
import java.util.Map;

@RestController
@RequestMapping("/api/status")
@RequiredArgsConstructor
@Tag(name = "사업자 등록 상태조회", description = "국세청 API를 통해 사업자등록 상태를 조회하고 JWT 토큰을 발급받는 API입니다.")
public class BusinessStatusController {

    private final BusinessStatusService businessStatusService;
    private final JwtUtil jwtUtil;

    @PostMapping("/verify")
    @Operation(
            summary = "사업자 등록 상태 확인",
            description = "사업자등록번호를 통해 유효한 사업자인지 확인하고, 유효 시 JWT 토큰을 발급합니다.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "사업자등록번호 요청 DTO",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = BusinessStatusRequestDTO.class),
                            examples = @ExampleObject(
                                    value = "{\n  \"b_no\": [\"1234567890\"]\n}"
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "유효한 사업자인 경우 JWT 토큰 포함 응답 반환",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            value = "{\n" +
                                                    "  \"status\": {\n" +
                                                    "    \"data\": [\n" +
                                                    "      {\n" +
                                                    "        \"b_no\": \"1234567890\",\n" +
                                                    "        \"b_stt\": \"계속사업자\",\n" +
                                                    "        \"tax_type\": \"일반과세자\"\n" +
                                                    "      }\n" +
                                                    "    ]\n" +
                                                    "  },\n" +
                                                    "  \"token\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6...\"\n" +
                                                    "}"
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "500", description = "서버 오류 또는 API 실패")
            }
    )
    public ResponseEntity<Map<String, Object>> getBusinessStatus(@RequestBody BusinessStatusRequestDTO requestDTO) {
        BusinessStatusResponseDTO responseDTO = businessStatusService.getBusinessStatus(requestDTO);

        Map<String, Object> result = new HashMap<>();
        result.put("status", responseDTO);

        // 유효한 사업자가 있을 경우 JWT 발급
        if (responseDTO.getData() != null && !responseDTO.getData().isEmpty()) {
            String b_no = responseDTO.getData().get(0).getB_no(); // 첫 번째 사업자 기준
            String token = jwtUtil.createToken(b_no);
            result.put("token", token);
        }

        return ResponseEntity.ok(result);
    }
}
