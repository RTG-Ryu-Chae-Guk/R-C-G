package com.rcg.citydata.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rcg.citydata.dto.CityDynamicDto;
import com.rcg.citydata.entity.CityDynamic;
import org.springframework.stereotype.Component;

/**
 * CityDynamic 엔터티의 dynamicData(JSON)를 CityDynamicDto로 변환하는 컨버터 클래스
 */
@Component
public class CityDynamicConverter {

    private final ObjectMapper objectMapper;

    public CityDynamicConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public CityDynamicDto convertToDto(CityDynamic cityDynamic) {
        try {
            return objectMapper.readValue(cityDynamic.getDynamicData(), CityDynamicDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("CityDynamic.dynamicData JSON 파싱 실패: " + e.getMessage(), e);
        }
    }


}