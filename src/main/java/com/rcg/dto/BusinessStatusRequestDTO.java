package com.rcg.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "사업자등록 상태조회 요청 DTO")
public class BusinessStatusRequestDTO {

    @Schema(
            description = "사업자등록번호 리스트 (최대 100개)",
            example = "[\"1234567890\"]",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private List<String> b_no;

    public List<String> getB_no() {
        return b_no;
    }

    public void setB_no(List<String> b_no) {
        this.b_no = b_no;
    }
}
