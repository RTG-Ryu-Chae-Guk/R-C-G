package com.rcg.citydata.repository;

import com.rcg.citydata.entity.CityDynamic;
import com.rcg.citydata.entity.CityDynamicId;
import com.rcg.citydata.entity.CityStatic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CityDynamicRepository extends JpaRepository<CityDynamic, CityDynamicId> {

  Optional<CityDynamic> findTopByIdAreaNmOrderByIdMeasuredAtDesc(String areaNm);

  Optional<CityDynamic> findTopByIdAreaCdOrderByIdMeasuredAtDesc(String areaCd);

  void deleteByIdAreaNm(String areaNm);
}
