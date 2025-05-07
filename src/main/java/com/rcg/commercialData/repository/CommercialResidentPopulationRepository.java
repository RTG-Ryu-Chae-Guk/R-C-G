package com.rcg.commercialData.repository;

import com.rcg.commercialData.entity.CommercialResidentPopulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CommercialResidentPopulationRepository extends JpaRepository<CommercialResidentPopulation, Long> {
    Optional<CommercialResidentPopulation> findByTrdarCdAndStdrYyquCd(String trdarCd, String stdrYyquCd);
    Optional<CommercialResidentPopulation> findTopByTrdarCdOrderByStdrYyquCdDesc(String trdarCd);
}
