package com.rcg.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class AnonymousNameMappingId implements Serializable {
    private Long postId;

    @Column(name = "b_no")
    private String b_no;
}