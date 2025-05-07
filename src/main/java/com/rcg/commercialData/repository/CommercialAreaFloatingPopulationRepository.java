package com.rcg.commercialData.repository;

import com.rcg.commercialData.entity.CommercialAreaFloatingPopulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CommercialAreaFloatingPopulationRepository extends JpaRepository<  CommercialAreaFloatingPopulation, Long> {
    Optional<CommercialAreaFloatingPopulation> findByTrdarCdAndStdrYyquCd(String trdarCd, String stdrYyquCd);

    @Query("SELECT f.stdrYyquCd FROM CommercialAreaFloatingPopulation f " +
            "WHERE f.trdarCd = :trdarCd " +
            "ORDER BY f.stdrYyquCd DESC")
    Optional<String> findLatestYyquCdByTrdarCd(@Param("trdarCd") String trdarCd); // 가장 최신 분기

}
