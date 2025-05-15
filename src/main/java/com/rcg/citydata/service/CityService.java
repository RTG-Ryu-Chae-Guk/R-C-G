package com.rcg.citydata.service;


import com.rcg.citydata.converter.CityDynamicConverter;
import com.rcg.citydata.dto.CityDynamicDto;
import com.rcg.citydata.entity.CityDynamic;
import com.rcg.citydata.repository.CityDynamicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService {

  // private final CityStaticRepository repository;

  private final CityDynamicRepository repository;
  private final CityDynamicConverter converter;

  /** DB에 저장된 모든 지역 데이터를 DTO로 변환하여 반환 */
  public List<CityDynamicDto> findAll() {
    return repository.findAll().stream()
            .map(converter::convertToDto)
            .collect(Collectors.toList());
  }

  /** 지역 코드로 조회 */
  public CityDynamicDto findByAreaCd(String areaCd) {
    CityDynamic entity = repository.findTopByIdAreaCdOrderByIdMeasuredAtDesc(areaCd)
            .orElseThrow(() -> new NoSuchElementException("지역 코드 '" + areaCd + "' 없음"));
    return converter.convertToDto(entity);
  }

  /** 지역 이름으로 조회 (동적 테이블에는 areaNm 컬럼이 없을 수 있음 -> 별도 쿼리 필요 시 리포지토리에서 정의) */
  public CityDynamicDto findByAreaNm(String areaNm) {
    CityDynamic entity = repository.findTopByIdAreaNmOrderByIdMeasuredAtDesc(areaNm)
            .orElseThrow(() -> new NoSuchElementException("지역 이름 '" + areaNm + "' 없음"));
    return converter.convertToDto(entity);
  }



}