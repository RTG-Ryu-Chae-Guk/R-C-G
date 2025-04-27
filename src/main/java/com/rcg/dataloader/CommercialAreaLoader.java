package com.rcg.dataloader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.rcg.entity.CommercialArea;
import com.rcg.repository.CommercialAreaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CommercialAreaLoader implements CommandLineRunner {

    private final CommercialAreaRepository repository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (repository.count() > 0) {
            System.out.println("✅ 상권 영역 데이터 이미 데이터 있음, skip");
            return;
        }

        InputStream is = getClass().getResourceAsStream("/data/commercial_area.json");

        if (is == null) {
            throw new IllegalStateException("❌ JSON 파일 없음");
        }

        ObjectMapper mapper = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        JsonNode root = mapper.readTree(is); // JSON 전체 읽기
        JsonNode dataNode = root.get("DATA"); // "DATA" 필드만 가져오기

        if (dataNode == null || !dataNode.isArray()) {
            throw new IllegalStateException("❌ 'DATA' 필드가 배열이 아님");
        }

        List<CommercialArea> areaList = mapper.readValue(
                dataNode.traverse(), new TypeReference<>() {}
        );

        repository.saveAll(areaList);
        System.out.println("✅ 상권 데이터 삽입 완료: " + areaList.size() + "건");
    }
}
