package com.rcg.commercialData.repository;

import com.rcg.commercialData.entity.CommercialSpending;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommercialSpendingRepository extends JpaRepository<CommercialSpending, Long> {
    Optional<CommercialSpending> findByTrdarCdAndStdrYyquCd(String trdarCd, String stdrYyquCd);
}