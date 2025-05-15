package com.rcg.commercialData.repository;

import com.rcg.commercialData.entity.CommercialAreaStoreStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommercialAreaStoreStatusRepository extends JpaRepository<CommercialAreaStoreStatus, Long> {
    List<CommercialAreaStoreStatus> findAllByTrdarCdAndStdrYyquCd(String trdarCd, String stdrYyquCd);
    List<CommercialAreaStoreStatus> findTop100ByTrdarCdOrderByStdrYyquCdDesc(String trdarCd);

    @Query("SELECT MAX(s.stdrYyquCd) FROM CommercialAreaStoreStatus s WHERE s.trdarCd = :trdarCd")
    Optional<String> findLatestYyquCdByTrdarCd(@Param("trdarCd") String trdarCd);

} // 상권코드와 분기코드로 가져옴 결과가 여러개이므로 list.
