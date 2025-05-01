package com.rcg.citydata.repository;

import com.rcg.citydata.entity.CityStatic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityStaticRepository extends JpaRepository<CityStatic, String> {
  Optional<CityStatic> findByAreaNm(String areaNm);
}