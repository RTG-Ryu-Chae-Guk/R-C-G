package com.rcg.commercialData.repository;

import com.rcg.commercialData.entity.CommercialAreaSales;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommercialAreaSalesRepository extends JpaRepository<CommercialAreaSales, String> {
    Optional<CommercialAreaSales> findByTrdarCdAndStdrYyquCd(String trdarCd, String stdrYyquCd);}
