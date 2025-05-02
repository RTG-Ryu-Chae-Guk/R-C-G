package com.rcg.commercialData.repository;

import com.rcg.commercialData.entity.CommercialAreaFloatingPopulation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommercialAreaFloatingPopulationRepository extends JpaRepository<  CommercialAreaFloatingPopulation, Long> {
    Optional<CommercialAreaFloatingPopulation> findByTrdarCdAndStdrYyquCd(String trdarCd, String stdrYyquCd);

}
