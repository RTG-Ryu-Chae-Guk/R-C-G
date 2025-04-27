package com.rcg.repository;

import com.rcg.entity.AnonymousNameMapping;
import com.rcg.entity.AnonymousNameMappingId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnonymousNameMappingRepository extends JpaRepository<AnonymousNameMapping, AnonymousNameMappingId> {
    boolean existsById(AnonymousNameMappingId id);
    long countByIdPostId(Long postId);
}