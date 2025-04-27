package com.rcg.controller;

import com.rcg.dto.PostDTO;
import com.rcg.entity.Post;
import com.rcg.service.PostService;
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
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@Tag(name = "익명 게시판 API", description = "사업자 인증 기반 게시판 기능 제공")
public class PostController {

    private final PostService postService;

    @PostMapping
    @Operation(summary = "게시글 작성")
    public ResponseEntity<Post> writePost(@RequestBody PostDTO dto) {
        String bNo = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(postService.writePost(dto, bNo));
    }

    @GetMapping
    @Operation(summary = "전체 게시글 목록 조회")
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    @Operation(summary = "게시글 상세 조회 및 조회수 증가")
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPost(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "게시글 수정")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody PostDTO dto) {
        String bNo = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(postService.updatePost(id, dto, bNo));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "게시글 삭제")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        String bNo = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        postService.deletePost(id, bNo);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/like")
    @Operation(summary = "게시글 좋아요")
    public ResponseEntity<Void> likePost(@PathVariable Long id) {
        String bNo = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        postService.likePost(id, bNo);
        return ResponseEntity.ok().build();
    }
}
