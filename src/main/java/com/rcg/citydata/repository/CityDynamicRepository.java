package com.rcg.citydata.repository;

import com.rcg.citydata.entity.CityDynamic;
import com.rcg.citydata.entity.CityDynamicId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityDynamicRepository
    extends JpaRepository<CityDynamic, CityDynamicId> {

  List<CityDynamic> findByIdAreaNmOrderByIdMeasuredAtDesc(String areaNm);
}
