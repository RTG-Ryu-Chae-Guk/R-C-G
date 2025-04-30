package com.rcg.dataloader;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.rcg.community.entity.CommercialResidentPopulation;
import com.rcg.community.repository.CommercialResidentPopulationRepository;
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
public class CommercialResidentPopulationLoader implements CommandLineRunner {

    private final CommercialResidentPopulationRepository repository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (repository.count() > 0) {
            log.info("✅ 상권 상주인구 데이터 이미 있음, skip");
            return;
        }

        String path = "data/commercial_resident_population.csv";
        ClassPathResource resource = new ClassPathResource(path);

        if (!resource.exists()) {
            log.warn("⚠️ 파일 없음: {}", path);
            return;
        }

        try (InputStream is = resource.getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

            CsvToBean<CommercialResidentPopulation> csvToBean = new CsvToBeanBuilder<CommercialResidentPopulation>(br)
                    .withType(CommercialResidentPopulation.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<CommercialResidentPopulation> list = csvToBean.parse();
            repository.saveAll(list);
            log.info("✅ 상권 상주인구 데이터 삽입 완료: {}건", list.size());

        } catch (Exception e) {
            log.error("❌ 상권 상주인구 데이터 처리 중 오류: {}", e.getMessage(), e);
        }
    }
}
