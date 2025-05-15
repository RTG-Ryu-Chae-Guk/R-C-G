package com.rcg.commercialData.service;

import com.rcg.commercialData.dto.TopFloatingAreaDto;
import com.rcg.commercialData.entity.CommercialArea;
import com.rcg.commercialData.entity.CommercialAreaFloatingPopulation;
import com.rcg.commercialData.repository.CommercialAreaFloatingPopulationRepository;
import com.rcg.commercialData.repository.CommercialAreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommercialTopFloatingService {

    private final CommercialAreaRepository areaRepository;
    private final CommercialAreaFloatingPopulationRepository floatingRepository;

    public List<TopFloatingAreaDto> getTop10FloatingAreas(String stdrYyquCd) {
        List<CommercialAreaFloatingPopulation> all = floatingRepository.findAllByStdrYyquCd(stdrYyquCd);

        return all.stream()
                .filter(f -> f.getTotFlpopCo() != null)
                .sorted(Comparator.comparingInt(CommercialAreaFloatingPopulation::getTotFlpopCo).reversed())
                .limit(10)
                .map(f -> {
                    CommercialArea area = areaRepository.findByTrdarCd(f.getTrdarCd()).orElse(null);
                    return new TopFloatingAreaDto(
                            f.getTrdarCd(),
                            area != null ? area.getTrdarCdNm() : "",
                            f.getTotFlpopCo().longValue()
                    );
                })
                .collect(Collectors.toList());
    }
}
