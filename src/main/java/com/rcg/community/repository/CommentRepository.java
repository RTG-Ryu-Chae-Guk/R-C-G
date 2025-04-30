package com.rcg.community.repository;

import com.rcg.community.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
    long countByPostIdAndParentIdIsNull(Long postId);
}
