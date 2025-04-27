package com.rcg.controller;

import com.rcg.dto.BusinessStatusRequestDTO;
import com.rcg.dto.BusinessStatusResponseDTO;
import com.rcg.jwt.JwtUtil;
import com.rcg.service.BusinessStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/status")
@RequiredArgsConstructor
public class BusinessStatusController {

    private final BusinessStatusService businessStatusService;
    private final JwtUtil jwtUtil;

    @PostMapping
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