package com.rcg.commercialData.repository;

import com.rcg.commercialData.dto.CommercialAreaSalesDto;
import com.rcg.commercialData.entity.CommercialAreaSales;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommercialAreaSalesRepository extends JpaRepository<CommercialAreaSales, String> {
    List<CommercialAreaSales> findAllByTrdarCdAndStdrYyquCd(String trdarCd, String stdrYyquCd);

    CommercialAreaSales findTopByTrdarCdOrderByStdrYyquCdDesc(String trdarCd);

}