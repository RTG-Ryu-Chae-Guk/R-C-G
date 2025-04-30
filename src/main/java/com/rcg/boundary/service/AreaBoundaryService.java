package com.rcg.boundary.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rcg.boundary.dto.AreaBoundaryDTO;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class AreaBoundaryService {

    public AreaBoundaryDTO getAreaBoundaryByName(String areaName) {
        try (InputStream is = getClass().getResourceAsStream("/data/seoul_area_boundaries_116.json")) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(is);

            for (Iterator<String> it = root.fieldNames(); it.hasNext(); ) {
                String key = it.next();
                if (key.equals(areaName)) {
                    JsonNode coords = root.get(key);
                    List<List<Double>> coordinateList = new ArrayList<>();
                    for (JsonNode pair : coords) {
                        List<Double> latlng = List.of(pair.get(0).asDouble(), pair.get(1).asDouble());
                        coordinateList.add(latlng);
                    }
                    return new AreaBoundaryDTO(key, coordinateList);
                }
            }

            return null; // 해당 지역 없음
        } catch (Exception e) {
            throw new RuntimeException("경계 데이터 로딩 실패", e);
        }
    }
}