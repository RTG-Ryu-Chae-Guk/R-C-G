package com.rcg.dataloader;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.rcg.entity.CommercialAreaFloatingPopulation;
import com.rcg.repository.CommercialAreaFloatingPopulationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommercialAreaFloatingPopulationLoader implements CommandLineRunner {

    private final CommercialAreaFloatingPopulationRepository repository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (repository.count() > 0) {
            log.info("✅ 상권 유동인구 데이터 이미 있음, skip");
            return;
        }

        String path = "data/floating_population/commercial_area_floating_population.csv";
        ClassPathResource resource = new ClassPathResource(path);

        if (!resource.exists()) {
            log.warn("⚠️ 유동인구 파일 없음: {}", path);
            return;
        }

        try (InputStream is = resource.getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

            CsvToBean<CommercialAreaFloatingPopulation> csvToBean = new CsvToBeanBuilder<CommercialAreaFloatingPopulation>(br)
                    .withType(CommercialAreaFloatingPopulation.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<CommercialAreaFloatingPopulation> list = csvToBean.parse();
            repository.saveAll(list);
            log.info("✅ 상권 유동인구 전체 데이터 삽입 완료: {}건", list.size());

        } catch (Exception e) {
            log.error("❌ 유동인구 데이터 처리 오류: {}", e.getMessage(), e);
        }
    }
}