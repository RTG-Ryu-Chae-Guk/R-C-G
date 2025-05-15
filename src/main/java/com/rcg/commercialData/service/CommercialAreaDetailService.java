package com.rcg.commercialData.service;

import com.rcg.commercialData.converter.CommercialAreaDtoConverter;
import com.rcg.commercialData.dto.*;
import com.rcg.commercialData.entity.*;
import com.rcg.commercialData.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommercialAreaDetailService {

    private final CommercialAreaRepository areaRepository;
    private final CommercialAreaFloatingPopulationRepository floatingPopulationRepository;
    private final CommercialAreaSalesRepository salesRepository;
    private final CommercialAreaStoreStatusRepository storeStatusRepository;
    private final CommercialResidentPopulationRepository residentPopulationRepository;
    private final CommercialSpendingRepository spendingRepository;
    private final CommercialAreaDtoConverter converter;

    public CommercialAreaDto getArea(String trdarCd) {
        trdarCd = trdarCd.trim(); // 공백 제거
        CommercialArea area = areaRepository.findByTrdarCd(trdarCd)
                .orElseThrow(() -> new IllegalArgumentException("해당 상권 정보가 없습니다."));
        return converter.toDto(area);
    }


    public CommercialAreaFloatingPopulationDto getFloatingPopulation(String trdarCd, String stdrYyquCd) {
        log.info("🔍 [유동인구 조회 시작] trdarCd = {}, stdrYyquCd = {}", trdarCd, stdrYyquCd);

        try {
            if (stdrYyquCd == null) {
                log.warn("⚠️ stdrYyquCd가 null입니다. 기본 분기로 대체해야 합니다.");
                stdrYyquCd = floatingPopulationRepository.findLatestYyquCdByTrdarCd(trdarCd)
                        .orElseThrow(() -> new IllegalArgumentException("기준 분기 코드를 찾을 수 없습니다."));
                log.info("✅ 최신 기준 분기 코드로 대체됨: {}", stdrYyquCd);
            }

            var entity = floatingPopulationRepository
                    .findByTrdarCdAndStdrYyquCd(trdarCd, stdrYyquCd)
                    .orElseThrow(() -> new IllegalArgumentException("해당 조건의 유동인구 데이터가 존재하지 않습니다."));

            log.info("✅ 유동인구 데이터 조회 성공: entity = {}", entity);
            return converter.toDto(entity);

        } catch (IllegalArgumentException e) {
            log.warn("❌ 유동인구 조회 실패: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("🔥 예상치 못한 오류 발생", e);
            throw e;
        }
    }

    public List<TopStoreCategoryDto> getTop5StoreCategories(String trdarCd, String stdrYyquCd) {
        if (stdrYyquCd == null) {
            stdrYyquCd = storeStatusRepository.findLatestYyquCdByTrdarCd(trdarCd)
                    .orElseThrow(() -> new IllegalArgumentException("기준 분기 코드 없음"));
        }

        List<CommercialAreaStoreStatus> list = storeStatusRepository.findAllByTrdarCdAndStdrYyquCd(trdarCd, stdrYyquCd);

        return list.stream()
                .filter(s -> s.getStoreCo() != null && s.getCloseRt() != null)
                .sorted(Comparator
                        .comparing(CommercialAreaStoreStatus::getStoreCo, Comparator.reverseOrder())
                        .thenComparing(CommercialAreaStoreStatus::getCloseRt))
                .limit(5)
                .map(s -> new TopStoreCategoryDto(s.getSvcIndutyCdNm(), s.getStoreCo(), s.getCloseRt()))
                .toList();
    }

    public List<CommercialAreaSalesDto> getSales(String trdarCd, String stdrYyquCd) {
        List<CommercialAreaSales> salesList = salesRepository.findAllByTrdarCdAndStdrYyquCd(trdarCd, stdrYyquCd);
        return converter.toSalesDtoList(salesList);
    }

    public List<CommercialAreaStoreStatusDto> getStoreStatus(String trdarCd, String stdrYyquCd) {
        List<CommercialAreaStoreStatus> storeStatusList = storeStatusRepository
                .findAllByTrdarCdAndStdrYyquCd(trdarCd, stdrYyquCd);
        return converter.toStoreStatusDtoList(storeStatusList);
    }

    public CommercialResidentPopulationDto getResidentPopulation(String trdarCd, String stdrYyquCd) {
        CommercialResidentPopulation resident = residentPopulationRepository
                .findByTrdarCdAndStdrYyquCd(trdarCd, stdrYyquCd)
                .orElseThrow(() -> new IllegalArgumentException("상주인구 데이터가 없습니다."));
        return converter.toDto(resident);
    }

    public CommercialSpendingDto getSpending(String trdarCd, String stdrYyquCd) {
        CommercialSpending spending = spendingRepository
                .findByTrdarCdAndStdrYyquCd(trdarCd, stdrYyquCd)
                .orElseThrow(() -> new IllegalArgumentException("소비 데이터가 없습니다."));
        return converter.toDto(spending);
    }

    public CommercialAreaDetailDto getSummary(String trdarCd, String stdrYyquCd) {
        return CommercialAreaDetailDto.builder()
                .area(getArea(trdarCd))
                .floatingPopulation(getFloatingPopulation(trdarCd, stdrYyquCd))
                .salesList(getSales(trdarCd, stdrYyquCd))
                .storeStatusList(getStoreStatus(trdarCd, stdrYyquCd))
                .residentPopulation(getResidentPopulation(trdarCd, stdrYyquCd))
                .spending(getSpending(trdarCd, stdrYyquCd))
                .build();
    }
}
