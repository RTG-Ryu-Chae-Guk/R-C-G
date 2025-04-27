package com.rcg.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long postId;
    private Long parentId; // null이면 댓글, 아니면 대댓글

    @Column(name = "b_no")
    private String b_no;

    private String content;
    private int likeCount = 0;
    private LocalDateTime createdAt = LocalDateTime.now();
}