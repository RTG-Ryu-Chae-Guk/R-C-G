package com.rcg.community.service;

import com.rcg.community.dto.CommentDTO;
import com.rcg.community.entity.AnonymousNameMapping;
import com.rcg.community.entity.AnonymousNameMappingId;
import com.rcg.community.entity.Comment;
import com.rcg.community.repository.AnonymousNameMappingRepository;
import com.rcg.community.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final AnonymousNameMappingRepository anonymousRepo;

    @Transactional
    public Comment writeComment(Long postId, CommentDTO dto, String b_no) {
        AnonymousNameMappingId id = new AnonymousNameMappingId();
        id.setPostId(postId);
        id.setB_no(b_no);

        if (!anonymousRepo.existsById(id)) {
            long count = anonymousRepo.countByIdPostId(postId);
            String name = "익명" + count;
            AnonymousNameMapping mapping = new AnonymousNameMapping();
            mapping.setPostId(postId);
            mapping.setB_no(b_no);
            mapping.setAnonymousName(name);
            anonymousRepo.save(mapping);
        }

        Comment comment = new Comment();
        comment.setPostId(postId);
        comment.setParentId(dto.getParentId());
        comment.setB_no(b_no);
        comment.setContent(dto.getContent());

        return commentRepository.save(comment);
    }

    public List<Comment> getComments(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public void deleteComment(Long commentId, String b_no) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다."));
        if (!comment.getB_no().equals(b_no)) {
            throw new SecurityException("작성자만 삭제할 수 있습니다.");
        }
        commentRepository.delete(comment);
    }

    @Transactional
    public void likeComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다."));
        comment.setLikeCount(comment.getLikeCount() + 1);
    }
}