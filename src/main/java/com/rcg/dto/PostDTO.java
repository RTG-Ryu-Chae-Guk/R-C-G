// PostDTO.java
package com.rcg.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "게시글 작성/수정 DTO")
@Getter
@Setter
public class PostDTO {
    @Schema(description = "게시글 제목", example = "첫 번째 게시글입니다")
    private String title;

    @Schema(description = "게시글 내용", example = "내용을 입력하세요")
    private String content;
}