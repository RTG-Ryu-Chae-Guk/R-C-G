package com.rcg.community.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AnonymousNameMapping {

    @EmbeddedId
    private AnonymousNameMappingId id;

    @Column(nullable = false)
    private String anonymousName;

    public void setPostId(Long postId) {
        if (id == null) id = new AnonymousNameMappingId();
        id.setPostId(postId);
    }

    public void setB_no(String b_no) {
        if (id == null) id = new AnonymousNameMappingId();
        id.setB_no(b_no);
    }

    public Long getPostId() {
        return id.getPostId();
    }

    public String getB_no() {
        return id.getB_no();
    }
}