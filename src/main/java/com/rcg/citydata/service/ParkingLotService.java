package com.rcg.citydata.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rcg.citydata.entity.CityDynamic;
import com.rcg.citydata.dto.CityDynamicDto.ParkingLotStts;
import com.rcg.citydata.repository.CityDynamicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParkingLotService {

    private final CityDynamicRepository dynamicRepository;
    private final ObjectMapper objectMapper;

    /**
     * DB에 저장된 모든 CityDynamic의 dynamicData 필드를 파싱하여
     * ParkingLotStts DTO 리스트로 반환
     */
    public List<ParkingLotStts> findAllParkingLots() {
        return dynamicRepository.findAll().stream()
                .map(this::toParkingDto)
                .collect(Collectors.toList());
    }

    /**
     * 주차장 코드(prkCd)로 주차장 정보 조회
     */
    public ParkingLotStts findByPrkCd(String prkCd) {
        return dynamicRepository.findAll().stream()
                .map(this::toParkingDto)
                .filter(dto -> prkCd.equals(dto.getPrkCd()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("주차장 코드 '" + prkCd + "'에 해당하는 정보가 없습니다."));
    }

    /**
     * CityDynamic.dynamicData(JSON String)를 ParkingLotStts로 매핑
     */
    private ParkingLotStts toParkingDto(CityDynamic entity) {
        try {
            return objectMapper.readValue(entity.getDynamicData(), ParkingLotStts.class);
        } catch (Exception e) {
            throw new RuntimeException("주차장 데이터 파싱 오류 (areaCd=" + entity.getId().getAreaCd() + ")", e);
        }
    }
}
