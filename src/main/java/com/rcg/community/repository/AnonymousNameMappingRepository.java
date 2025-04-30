package com.rcg.community.repository;

import com.rcg.community.entity.AnonymousNameMapping;
import com.rcg.community.entity.AnonymousNameMappingId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnonymousNameMappingRepository extends JpaRepository<AnonymousNameMapping, AnonymousNameMappingId> {
    boolean existsById(AnonymousNameMappingId id);
    long countByIdPostId(Long postId);
}