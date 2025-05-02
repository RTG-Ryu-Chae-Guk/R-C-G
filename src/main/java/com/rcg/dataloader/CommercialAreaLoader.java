package com.rcg.dataloader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.rcg.commercialData.entity.CommercialArea;
import com.rcg.commercialData.repository.CommercialAreaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CommercialAreaLoader implements CommandLineRunner {

    private final CommercialAreaRepository repository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        InputStream is = getClass().getResourceAsStream("/data/commercial_area.json");

        if (is == null) {
            throw new IllegalStateException("❌ JSON 파일 없음");
        }

        ObjectMapper mapper = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        JsonNode root = mapper.readTree(is);
        JsonNode features = root.get("features");

        if (features == null || !features.isArray()) {
            throw new IllegalStateException("❌ 'features' 필드가 배열이 아님");
        }

        // DB에 이미 존재하는 상권 코드 목록 조회
        Set<String> existingCodes = repository.findAll().stream()
                .map(CommercialArea::getTrdarCd)
                .collect(Collectors.toSet());

        List<CommercialArea> newAreas = new ArrayList<>();
        int skipped = 0;

        for (JsonNode feature : features) {
            JsonNode props = feature.get("properties");
            JsonNode geometry = feature.get("geometry");
            JsonNode coords = geometry.get("coordinates");

            String trdarCd = props.get("trdar_cd").asText();

            // ✅ 이미 존재하는 상권이면 무시
            if (existingCodes.contains(trdarCd)) {
                skipped++;
                continue;
            }

            CommercialArea area = new CommercialArea();
            area.setTrdarCd(trdarCd);
            area.setTrdarCdNm(props.get("trdar_cd_nm").asText());
            area.setAdstrdCdNm(props.get("adstrd_cd_nm").asText());
            area.setSignguCdNm(props.get("signgu_cd_nm").asText());
            area.setRelmAr(props.get("relm_ar").asDouble());
            area.setLongitude(coords.get(0).asDouble());
            area.setLatitude(coords.get(1).asDouble());

            newAreas.add(area);
        }

        repository.saveAll(newAreas);

        System.out.println("✅ 신규 상권 저장 완료: " + newAreas.size() + "건");
        System.out.println("ℹ️ 이미 존재해서 무시된 상권: " + skipped + "건");
    }
}
