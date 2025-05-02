package com.rcg.commercialData.repository;

import com.rcg.commercialData.entity.CommercialResidentPopulation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommercialResidentPopulationRepository extends JpaRepository<CommercialResidentPopulation, Long> {
    Optional<CommercialResidentPopulation> findByTrdarCdAndStdrYyquCd(String trdarCd, String stdrYyquCd);}
