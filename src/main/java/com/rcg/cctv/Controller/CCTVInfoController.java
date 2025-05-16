package com.rcg.cctv.Controller;

import com.rcg.cctv.Entity.CCTVInfo;
import com.rcg.cctv.Service.CCTVInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cctvs")
@RequiredArgsConstructor
@Tag(name = "CCTV API", description = "국도 CCTV 정보 수집 및 조회 API")
public class CCTVInfoController {

    private final CCTVInfoService cctvInfoService;

    @PostMapping("/fetch")
    @Operation(summary = "CCTV 정보 저장", description = "외부 OpenAPI에서 CCTV 정보를 가져와 DB에 저장합니다.")
    public ResponseEntity<String> fetchAndSaveCCTVInfo() {
        cctvInfoService.fetchAndSaveCCTVData(); // ✔ 올바른 메서드명으로 수정
        return ResponseEntity.ok("CCTV 정보가 성공적으로 저장되었습니다.");
    }

    @GetMapping
    @Operation(summary = "CCTV 정보 조회", description = "DB에 저장된 CCTV 정보를 전체 조회합니다.")
    public ResponseEntity<List<CCTVInfo>> getAllCCTVInfo() {
        List<CCTVInfo> list = cctvInfoService.getAllCCTVs(); // ✔ 올바른 메서드명으로 수정
        return ResponseEntity.ok(list);
    }
}
