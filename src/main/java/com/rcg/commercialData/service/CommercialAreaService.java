package com.rcg.commercialData.service;

import com.rcg.commercialData.converter.CommercialAreaDtoConverter;
import com.rcg.commercialData.dto.CommercialAreaDetailDto;
import com.rcg.commercialData.dto.CommercialAreaDto;
import com.rcg.commercialData.entity.*;
import com.rcg.commercialData.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommercialAreaService {

    private final CommercialAreaRepository areaRepository;
    private final CommercialAreaFloatingPopulationRepository floatingPopulationRepository;
    private final CommercialAreaSalesRepository salesRepository;
    private final CommercialAreaStoreStatusRepository storeStatusRepository;
    private final CommercialResidentPopulationRepository residentPopulationRepository;
    private final CommercialSpendingRepository spendingRepository;
    private final CommercialAreaDtoConverter converter;

    public CommercialAreaDetailDto getCommercialAreaDetail(String trdarCd, String stdrYyquCd) {
        CommercialArea area = areaRepository.findByTrdarCd(trdarCd)
                .orElseThrow(() -> new IllegalArgumentException("해당 상권 정보가 없습니다."));

        CommercialAreaFloatingPopulation floating = floatingPopulationRepository
                .findByTrdarCdAndStdrYyquCd(trdarCd, stdrYyquCd).orElse(null);

        CommercialAreaSales sales = salesRepository
                .findByTrdarCdAndStdrYyquCd(trdarCd, stdrYyquCd).orElse(null);

        CommercialAreaStoreStatus storeStatus = storeStatusRepository
                .findByTrdarCdAndStdrYyquCd(trdarCd, stdrYyquCd).orElse(null);

        CommercialResidentPopulation resident = residentPopulationRepository
                .findByTrdarCdAndStdrYyquCd(trdarCd, stdrYyquCd).orElse(null);

        CommercialSpending spending = spendingRepository
                .findByTrdarCdAndStdrYyquCd(trdarCd, stdrYyquCd).orElse(null);

        return CommercialAreaDetailDto.builder()
                .area(converter.toDto(area))
                .floatingPopulation(converter.toDto(floating))
                .sales(converter.toDto(sales))
                .storeStatus(converter.toDto(storeStatus))
                .residentPopulation(converter.toDto(resident))
                .spending(converter.toDto(spending))
                .build();
    }

    public List<CommercialAreaDto> getAreasInBoundingBox(Double minLat, Double maxLat, Double minLng, Double maxLng) {
        return areaRepository
                .findByLatitudeBetweenAndLongitudeBetween(minLat, maxLat, minLng, maxLng)
                .stream()
                .map(area -> CommercialAreaDto.builder()
                        .trdarCd(area.getTrdarCd())
                        .trdarCdNm(area.getTrdarCdNm())
                        .latitude(area.getLatitude())
                        .longitude(area.getLongitude())
                        .signguCdNm(area.getSignguCdNm())
                        .adstrdCdNm(area.getAdstrdCdNm())
                        .relmAr(area.getRelmAr())
                        .build())
                .collect(Collectors.toList());
    }

    // 지도에 위경도 좌표를 통해 상권 영역 표시 (원 형태로)
    public List<CommercialAreaDto> getAllCommercialAreas() {
        return areaRepository.findAll().stream()
                .map(area -> CommercialAreaDto.builder()
                        .trdarCd(area.getTrdarCd())
                        .trdarCdNm(area.getTrdarCdNm())
                        .longitude(area.getLongitude())
                        .latitude(area.getLatitude())
                        .signguCdNm(area.getSignguCdNm())
                        .adstrdCdNm(area.getAdstrdCdNm())
                        .relmAr(area.getRelmAr())
                        .build())
                .collect(Collectors.toList());
    }



}
