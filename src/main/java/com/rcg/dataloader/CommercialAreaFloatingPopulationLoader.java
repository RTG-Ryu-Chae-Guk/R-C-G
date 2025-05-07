package com.rcg.dataloader;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.rcg.commercialData.entity.CommercialAreaFloatingPopulation;
import com.rcg.commercialData.repository.CommercialAreaFloatingPopulationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import java.io.*;
import java.nio.charset.Charset;
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

        try (
                InputStream is = resource.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, Charset.forName("EUC-KR"));
                BufferedReader br = new BufferedReader(new BOMStrippedReader(isr))
        ) {
            CsvToBean<CommercialAreaFloatingPopulation> csvToBean = new CsvToBeanBuilder<CommercialAreaFloatingPopulation>(br)
                    .withType(CommercialAreaFloatingPopulation.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(',') // ✅ 탭 구분자 명시
                    .build();

            List<CommercialAreaFloatingPopulation> list = csvToBean.parse();
            repository.saveAll(list);
            log.info("✅ 상권 유동인구 전체 데이터 삽입 완료: {}건", list.size());

        } catch (Exception e) {
            log.error("❌ 유동인구 데이터 처리 오류: {}", e.getMessage(), e);
        }
    }

    /**
     * BOM 제거용 Reader
     */
    public static class BOMStrippedReader extends Reader {
        private final Reader reader;
        private boolean isFirstRead = true;

        public BOMStrippedReader(Reader reader) {
            this.reader = reader;
        }

        @Override
        public int read(char[] cbuf, int off, int len) throws IOException {
            int result = reader.read(cbuf, off, len);
            if (isFirstRead && result > 0) {
                isFirstRead = false;
                if (cbuf[0] == '\uFEFF') {
                    System.arraycopy(cbuf, 1, cbuf, 0, result - 1);
                    return result - 1;
                }
            }
            return result;
        }

        @Override
        public void close() throws IOException {
            reader.close();
        }
    }
}
