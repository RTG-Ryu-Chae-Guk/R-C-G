package com.rcg.news.Controller;

import com.rcg.Chatgpt.Service.ChatGptService;
import com.rcg.news.Dto.NewsDTO;
import com.rcg.news.Service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/gptnews")
@RequiredArgsConstructor
@Tag(name = "News Summary", description = "뉴스 요약 통합 API")
public class NewsSummaryController {

    private final NewsService newsService;
    private final ChatGptService chatGptService;

    @GetMapping("/summary")
    @Operation(
            summary = "키워드로 뉴스 요약",
            description = "뉴스 기사를 검색하고 전체 기사를 ChatGPT를 통해 종합 요약합니다.",
            parameters = {
                    @Parameter(name = "query", description = "검색 키워드", example = "소상공인혜택")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "요약 성공"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            }
    )
    public ResponseEntity<String> getSummarizedNews(@RequestParam String query) {
        List<NewsDTO> newsList = newsService.news(query);

        // 1. 뉴스 제목+본문 합치기
        StringBuilder combinedContent = new StringBuilder();
        for (NewsDTO news : newsList) {
            combinedContent.append("제목: ").append(news.getTitle()).append("\n")
                    .append("내용: ").append(news.getDescription()).append("\n\n");
        }

        // 2. ChatGPT 프롬프트 작성
        String prompt = String.format(
                "다음은 '%s' 키워드로 검색된 뉴스 기사 10건입니다.\n" +
                        "이 기사들을 모두 종합적으로 읽고 소상공인에게 어떤 시사점이나 혜택이 있는지 한 문단으로 요약해줘.\n\n%s",
                query, combinedContent
        );

        // 3. GPT 요청
        try {
            String summary = chatGptService.getChatGptResponse(prompt);
            return ResponseEntity.ok(summary);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("요약 실패: " + e.getMessage());
        }
    }

}

