// CommentController.java
package com.rcg.controller;

import com.rcg.dto.CommentDTO;
import com.rcg.entity.Comment;
import com.rcg.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@Tag(name = "댓글 API", description = "댓글/대댓글 및 좋아요 기능")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}")
    @Operation(summary = "댓글 또는 대댓글 작성")
    public ResponseEntity<Comment> writeComment(@PathVariable Long postId,
                                                @RequestBody CommentDTO dto) {
        String b_no = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(commentService.writeComment(postId, dto, b_no));
    }

    @GetMapping("/{postId}")
    @Operation(summary = "댓글 전체 조회")
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getComments(postId));
    }

    @DeleteMapping("/{commentId}")
    @Operation(summary = "댓글 삭제")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        String b_no = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        commentService.deleteComment(commentId, b_no);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{commentId}/like")
    @Operation(summary = "댓글 좋아요")
    public ResponseEntity<Void> likeComment(@PathVariable Long commentId) {
        commentService.likeComment(commentId);
        return ResponseEntity.ok().build();
    }
}