package com.rcg.commercialData.repository;

import com.rcg.commercialData.entity.CommercialArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommercialAreaRepository extends JpaRepository<CommercialArea, String> {
    Optional<CommercialArea> findByTrdarCd(String trdarCd);

    // 해당 범위 안의 상권만 조회
    List<CommercialArea> findByLatitudeBetweenAndLongitudeBetween(
            Double minLat, Double maxLat, Double minLng, Double maxLng);
}
