package com.rcg.RealEstateDeal.Service;

import com.rcg.RealEstateDeal.Entity.RealEstateDeal;
import com.rcg.RealEstateDeal.repository.RealEstateDealRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.StringReader;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class RealEstateDealService {

    private final RealEstateDealRepository dealRepository;

    private static final String[] VALID_CODES = {
            "11110", "11140", "11170", "11200", "11215", "11230",
            "11260", "11290", "11305", "11320", "11350", "11380",
            "11410", "11440", "11470", "11500", "11530", "11545",
            "11560", "11590", "11620", "11650", "11680", "11710", "11740"
    };

    private final WebClient webClient = WebClient.builder()
            .defaultHeader("User-Agent", "Mozilla/5.0")
            .defaultHeader("Referer", "https://www.data.go.kr")
            .defaultHeader("Accept", "*/*")
            .build();

    public void fetchAndSaveDeals() {
        String dealYmd = "202504";
        String decodedKey = "iifF6nWLmR+Pgzn+ZKQfpfbSa/xrnJe8cRoVMGBOYvaLg0iv2dluN+amznkrvFKRPIQHCZkfU4shudRWucbZag==";
        String encodedKey = URLEncoder.encode(decodedKey, StandardCharsets.UTF_8);

        for (String code : VALID_CODES) {
            try {
                String baseUrl = "https://apis.data.go.kr/1613000/RTMSDataSvcNrgTrade/getRTMSDataSvcNrgTrade";
                String query = String.format("?LAWD_CD=%s&DEAL_YMD=%s&serviceKey=%s",
                        URLEncoder.encode(code, StandardCharsets.UTF_8),
                        URLEncoder.encode(dealYmd, StandardCharsets.UTF_8),
                        encodedKey);
                URI uri = URI.create(baseUrl + query);

                String response = webClient.get()
                        .uri(uri)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();

                log.info("📡 요청 URL: {}", uri);
                log.info("📄 응답 내용: {}", response != null ? response.substring(0, Math.min(1000, response.length())) : "null");

                if (response == null || response.contains("SERVICE_KEY_IS_NOT_REGISTERED_ERROR")) continue;

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(new org.xml.sax.InputSource(new StringReader(response)));

                NodeList items = doc.getElementsByTagName("item");
                List<RealEstateDeal> dealList = new ArrayList<>();

                for (int i = 0; i < items.getLength(); i++) {
                    Element el = (Element) items.item(i);

                    RealEstateDeal deal = RealEstateDeal.builder()
                            .sggNm(getText(el, "sggNm"))
                            .umdNm(getText(el, "umdNm"))
                            .buildingUse(getText(el, "buildingUse"))
                            .buildingType(getText(el, "buildingType"))
                            .dealYear(Integer.parseInt(getText(el, "dealYear", "0")))
                            .dealMonth(Integer.parseInt(getText(el, "dealMonth", "0")))
                            .dealDay(Integer.parseInt(getText(el, "dealDay", "0")))
                            .dealAmount(getText(el, "dealAmount"))
                            .landUse(getText(el, "landUse"))
                            .buyerGbn(getText(el, "buyerGbn"))
                            .build();

                    dealList.add(deal);
                }

                dealRepository.saveAll(dealList);

            } catch (Exception e) {
                log.error("[{}] 부동산 거래 데이터 처리 실패", code, e);
            }
        }
    }

    private String getText(Element el, String tag) {
        NodeList list = el.getElementsByTagName(tag);
        return list.getLength() > 0 ? list.item(0).getTextContent().trim() : "";
    }

    private String getText(Element el, String tag, String defaultValue) {
        try {
            return getText(el, tag);
        } catch (Exception e) {
            return defaultValue;
        }
    }
}