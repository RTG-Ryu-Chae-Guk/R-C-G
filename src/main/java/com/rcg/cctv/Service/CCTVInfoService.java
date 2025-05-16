package com.rcg.cctv.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rcg.cctv.Entity.CCTVInfo;
import com.rcg.cctv.Repository.CCTVInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CCTVInfoService {

    private final CCTVInfoRepository cctvInfoRepository;

    @Value("${cctv.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public void fetchAndSaveCCTVData() {
        String url = "https://openapi.its.go.kr:9443/cctvInfo"
                + "?apiKey=" + apiKey
                + "&type=국도"
                + "&cctvType=1"
                + "&minX=126.734&maxX=127.2176"
                + "&minY=37.413&maxY=37.7085"
                + "&getType=json";

        try {
            String response = restTemplate.getForObject(url, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            JsonNode dataArray = root.path("response").path("data");

            List<CCTVInfo> cctvList = new ArrayList<>();

            for (JsonNode item : dataArray) {
                CCTVInfo cctv = CCTVInfo.builder()
                        .name(item.path("cctvname").asText())
                        .coordX(item.path("coordx").asDouble())
                        .coordY(item.path("coordy").asDouble())
                        .url(item.path("cctvurl").asText())
                        .build();
                cctvList.add(cctv);
            }

            cctvInfoRepository.saveAll(cctvList);

        } catch (Exception e) {
            log.error("Error fetching CCTV data", e);
        }
    }

    public List<CCTVInfo> getAllCCTVs() {
        return cctvInfoRepository.findAll();
    }
}