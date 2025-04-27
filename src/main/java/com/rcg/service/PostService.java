package com.rcg.service;

import com.rcg.dto.PostDTO;
import com.rcg.entity.AnonymousNameMapping;
import com.rcg.entity.Post;
import com.rcg.repository.AnonymousNameMappingRepository;
import com.rcg.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final AnonymousNameMappingRepository anonymousRepo;

    @Transactional
    public Post writePost(PostDTO dto, String b_no) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setB_no(b_no);

        Post saved = postRepository.save(post);

        AnonymousNameMapping name = new AnonymousNameMapping();
        name.setPostId(saved.getId());
        name.setB_no(b_no);
        name.setAnonymousName("익명(작성자)");
        anonymousRepo.save(name);

        return saved;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Transactional
    public Post getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));
        post.setViewCount(post.getViewCount() + 1);
        return post;
    }

    @Transactional
    public Post updatePost(Long id, PostDTO dto, String b_no) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        if (!post.getB_no().equals(b_no)) {
            throw new SecurityException("작성자만 수정할 수 있습니다.");
        }

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        return post;
    }

    @Transactional
    public void deletePost(Long id, String b_no) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        if (!post.getB_no().equals(b_no)) {
            throw new SecurityException("작성자만 삭제할 수 있습니다.");
        }

        postRepository.delete(post);
    }

    @Transactional
    public void likePost(Long id, String b_no) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        // 좋아요 중복 처리 생략. 추후 PostLike 테이블 추가 가능
        post.setLikeCount(post.getLikeCount() + 1);
    }
}