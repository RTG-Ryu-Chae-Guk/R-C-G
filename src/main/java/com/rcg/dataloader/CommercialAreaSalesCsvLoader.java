package com.rcg.dataloader;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.rcg.commercialData.entity.CommercialAreaSales;
import com.rcg.commercialData.repository.CommercialAreaSalesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CommercialAreaSalesCsvLoader implements CommandLineRunner {

    private final CommercialAreaSalesRepository repository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (repository.count() > 0) {
            System.out.println("✅ 상권 매출 데이터 이미 있음, skip");
            return;
        }

        String[] years = {"2020", "2021", "2022", "2023"};

        for (String year : years) {
            String path = "/data/sales/commercial_area_" + year + ".csv";
            try (InputStream is = getClass().getResourceAsStream(path);
                 BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

                CsvToBean<CommercialAreaSales> csvToBean = new CsvToBeanBuilder<CommercialAreaSales>(br)
                        .withType(CommercialAreaSales.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                List<CommercialAreaSales> salesList = csvToBean.parse();
                repository.saveAll(salesList);
                System.out.println("✅ " + year + "년 데이터 삽입 완료: " + salesList.size() + "건");

            } catch (Exception e) {
                System.err.println("❌ 파일 처리 실패: " + path + ", 에러: " + e.getMessage());
            }
        }
    }
}


