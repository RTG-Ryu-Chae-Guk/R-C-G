// CommentDTO.java
package com.rcg.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "댓글 작성 DTO")
@Getter
@Setter
public class CommentDTO {
    @Schema(description = "댓글 내용", example = "이건 댓글입니다.")
    private String content;

    @Schema(description = "부모 댓글 ID (댓글이면 null, 대댓글이면 부모 ID)", example = "null")
    private Long parentId;
}
