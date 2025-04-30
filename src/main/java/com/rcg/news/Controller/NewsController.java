package com.rcg.news.Controller;

import com.rcg.news.Dto.NewsDTO;
import com.rcg.news.Service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
@Tag(name = "News", description = "네이버 뉴스 검색 API")
public class NewsController {

    private final NewsService newsService;

    @GetMapping
    @Operation(
            summary = "기본 뉴스 조회",
            description = "'소상공인'이라는 기본 키워드로 최신 뉴스 10건을 조회합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "뉴스 조회 성공"),
                    @ApiResponse(responseCode = "500", description = "서버 내부 오류")
            }
    )
    public ResponseEntity<List<NewsDTO>> getDefaultNews() {
        List<NewsDTO> newsList = newsService.news("소상공인");
        return ResponseEntity.ok(newsList);
    }

    @GetMapping("/search")
    @Operation(
            summary = "키워드로 뉴스 검색",
            description = "사용자가 입력한 키워드로 뉴스 기사를 검색합니다.",
            parameters = {
                    @Parameter(name = "query", description = "검색할 키워드", example = "소상공인", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "검색 성공", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            }
    )
    public ResponseEntity<List<NewsDTO>> getNewsByKeyword(@RequestParam String query) {
        List<NewsDTO> newsList = newsService.news(query);
        return ResponseEntity.ok(newsList);
    }
}
