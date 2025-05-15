package com.rcg.Chatgpt.Controller;


import com.rcg.Chatgpt.Dto.CommercialAnalysisRequest;
import com.rcg.Chatgpt.Dto.CommercialAnalysisResponse;
import com.rcg.Chatgpt.Service.ChatGptService;
import com.rcg.commercialData.service.CommercialAreaDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
@Tag(name = "상권 GPT 분석", description = "상권 데이터를 바탕으로 GPT가 분석 및 업종 추천")
public class CommercialChatController {

    private final ChatGptService chatGptService;
    private final CommercialAreaDetailService detailService;

    @PostMapping("/analyze-commercial-area")
    @Operation(summary = "GPT 상권 분석", description = "상권 코드 및 분기 기준으로 GPT가 분석 및 점포 추천")
    public ResponseEntity<CommercialAnalysisResponse> analyze(
            @RequestParam String trdarCd,
            @RequestParam String stdrYyquCd) {

        System.out.println("📦 전달된 trdarCd: " + trdarCd);
        System.out.println("📦 전달된 stdrYyquCd: " + stdrYyquCd);

        // 여러 상권 데이터 통합 조회
        var area = detailService.getArea(trdarCd);
        var floating = detailService.getFloatingPopulation(trdarCd, stdrYyquCd);
        var residents = detailService.getResidentPopulation(trdarCd, stdrYyquCd);
        var sales = detailService.getSales(trdarCd, stdrYyquCd);
        var stores = detailService.getStoreStatus(trdarCd, stdrYyquCd);
        var spending = detailService.getSpending(trdarCd, stdrYyquCd);

        // GPT에게 전달할 프롬프트 생성
        String prompt = "다음은 상권 데이터입니다:\n" +
                "- 상권 이름: " + area.getTrdarCdNm() + "\n" +
                "- 위치: " + area.getSignguCdNm() + " " + area.getAdstrdCdNm() + "\n" +
                "- 유동인구: 총 " + floating.getTotFlpopCo() + "명\n" +
                "- 상주인구: 총 " + residents.getTotResidPopltnCnt() + "명\n" +
                "- 총 지출 금액: " + spending.getTotalExpendAmt() + "원\n" +
                "- 매출 주요 업종: " + (sales.size() > 0 ? sales.get(0).getSvcIndutyCdNm() : "없음") + "\n" +
                "- 점포 수: " + (stores.size() > 0 ? stores.get(0).getStoreCo() : "정보 없음") + "개\n" +
                "\n위 상권을 GPT가 분석하고, 가장 적합한 점포 업종을 추천해줘. 이유는 자세하게 설명해줘.";

        String gptResult = chatGptService.getChatGptResponse(prompt);

        return ResponseEntity.ok(new CommercialAnalysisResponse(
                gptResult,  // 이 안에 분석 + 추천 다 포함돼 있어도 됨
                "GPT 결과를 기반으로 추천됨"
        ));
    }
}
