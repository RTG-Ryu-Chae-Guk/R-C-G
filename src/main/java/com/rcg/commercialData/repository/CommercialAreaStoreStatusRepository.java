package com.rcg.commercialData.repository;

import com.rcg.commercialData.entity.CommercialAreaStoreStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommercialAreaStoreStatusRepository extends JpaRepository<CommercialAreaStoreStatus, Long> {
    Optional<CommercialAreaStoreStatus> findByTrdarCdAndStdrYyquCd(String trdarCd, String stdrYyquCd);} // 상권코드와 분기코드로 가져옴.
