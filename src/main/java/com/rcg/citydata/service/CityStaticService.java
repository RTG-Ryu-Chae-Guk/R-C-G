package com.rcg.citydata.service;

import com.rcg.citydata.dto.CityStaticDto;
import com.rcg.citydata.entity.CityStatic;
import com.rcg.citydata.repository.CityStaticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityStaticService {
  private final CityStaticRepository repository;

  /** DB에 저장된 모든 지역 데이터를 DTO로 변환하여 반환 */
  public List<CityStaticDto> findAll() {
    return repository.findAll().stream()
        .map(this::toDto)
        .collect(Collectors.toList());
  }

  /** 지역 코드로 조회 */
  public CityStaticDto findByAreaCd(String areaCd) {
    CityStatic cs = repository.findById(areaCd)
        .orElseThrow(() -> new NoSuchElementException("지역 코드 '" + areaCd + "' 없음"));
    return toDto(cs);
  }

  /** 지역 이름(AREA_NM)으로 조회 */
  public CityStaticDto findByAreaNm(String areaNm) {
    CityStatic cs = repository.findByAreaNm(areaNm)
        .orElseThrow(() -> new NoSuchElementException("지역 이름 '" + areaNm + "' 없음"));
    return toDto(cs);
  }

  private CityStaticDto toDto(CityStatic cs) {
    CityStaticDto dto = new CityStaticDto();
    dto.setAreaCd(cs.getAreaCd());
    dto.setAreaNm(cs.getAreaNm());
    dto.setAreaCongestLvl(cs.getAreaCongestLvl());
    dto.setAreaCongestMsg(cs.getAreaCongestMsg());
    dto.setMalePpltnRate(cs.getMalePpltnRate());
    dto.setFemalePpltnRate(cs.getFemalePpltnRate());
    dto.setResntPpltnRate(cs.getResntPpltnRate());
    dto.setNonResntPpltnRate(cs.getNonResntPpltnRate());
    dto.setSubStnNm(cs.getSubStnNm());
    dto.setSubStnLine(cs.getSubStnLine());
    dto.setSubStnRaddr(cs.getSubStnRaddr());
    dto.setSubStnJibun(cs.getSubStnJibun());
    dto.setSubStnX(cs.getSubStnX());
    dto.setSubStnY(cs.getSubStnY());
    dto.setSubNtStn(cs.getSubNtStn());
    dto.setSubBfStn(cs.getSubBfStn());
    dto.setSubRouteNm(cs.getSubRouteNm());
    dto.setSubLine(cs.getSubLine());
    dto.setBusStnId(cs.getBusStnId());
    dto.setBusArsId(cs.getBusArsId());
    dto.setBusStnNm(cs.getBusStnNm());
    dto.setBusStnX(cs.getBusStnX());
    dto.setBusStnY(cs.getBusStnY());
    dto.setRteStnNm(cs.getRteStnNm());
    dto.setRteNm(cs.getRteNm());
    dto.setRteId(cs.getRteId());
    dto.setRteSect(cs.getRteSect());
    dto.setRteCongest(cs.getRteCongest());
    dto.setRsbLrgCtgr(cs.getRsbLrgCtgr());
    dto.setRsbMidCtgr(cs.getRsbMidCtgr());
    dto.setRsbMctCnt(cs.getRsbMctCnt());
    dto.setRsbMctTime(cs.getRsbMctTime());
    dto.setCmrclMaleRate(String.valueOf(cs.getCmrclMaleRate()));
    dto.setCmrclFemaleRate(String.valueOf(cs.getCmrclFemaleRate()));
    dto.setCmrcl10Rate(String.valueOf(cs.getCmrcl10Rate()));
    dto.setCmrcl20Rate(String.valueOf(cs.getCmrcl20Rate()));
    dto.setCmrcl30Rate(String.valueOf(cs.getCmrcl30Rate()));
    dto.setCmrcl40Rate(String.valueOf(cs.getCmrcl40Rate()));
    dto.setCmrcl50Rate(String.valueOf(cs.getCmrcl50Rate()));
    dto.setCmrcl60Rate(String.valueOf(cs.getCmrcl60Rate()));
    dto.setCmrclPersonalRate(String.valueOf(cs.getCmrclPersonalRate()));
    dto.setCmrclCorporationRate(String.valueOf(cs.getCmrclCorporationRate()));
    return dto;
  }
}