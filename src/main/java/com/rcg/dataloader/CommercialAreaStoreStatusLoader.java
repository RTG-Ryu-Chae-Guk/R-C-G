package com.rcg.dataloader;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.rcg.commercialData.entity.CommercialAreaStoreStatus;
import com.rcg.commercialData.repository.CommercialAreaStoreStatusRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommercialAreaStoreStatusLoader implements CommandLineRunner {

    private final CommercialAreaStoreStatusRepository repository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (repository.count() > 0) {
            log.info("✅ 상권 점포 현황 데이터 이미 있음, skip");
            return;
        }

        String[] years = {"2020", "2021", "2022", "2023", "2024"};

        for (String year : years) {
            String path = String.format("data/store_status/commercial_area_store_status_%s.csv", year);
            ClassPathResource resource = new ClassPathResource(path);

            if (!resource.exists()) {
                log.warn("⚠️ 파일 없음, 건너뜀: {}", path);
                continue;
            }

            try (InputStream is = resource.getInputStream();
                 BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

                CsvToBean<CommercialAreaStoreStatus> csvToBean = new CsvToBeanBuilder<CommercialAreaStoreStatus>(br)
                        .withType(CommercialAreaStoreStatus.class)
                        .withSeparator(',')
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                List<CommercialAreaStoreStatus> list = csvToBean.parse();
                repository.saveAll(list);
                log.info("✅ {}년 상권 점포 현황 데이터 삽입 완료: {}건", year, list.size());

            } catch (Exception e) {
                log.error("❌ {}년 점포 현황 데이터 처리 오류: {}", year, e.getMessage(), e);
            }
        }
    }
}
