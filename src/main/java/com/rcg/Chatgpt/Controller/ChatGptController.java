package com.rcg.Chatgpt.Controller;


import com.rcg.Chatgpt.Dto.MessageRequest;
import com.rcg.Chatgpt.Service.ChatGptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
@Tag(name = "ChatGPT", description = "ChatGPT와 대화하는 API")
public class ChatGptController {

    private final ChatGptService chatGptService;

    @PostMapping
    @Operation(
            summary = "ChatGPT에 메시지 전송",
            description = "사용자가 입력한 메시지를 OpenAI ChatGPT 모델에 전달하고 응답을 받아옵니다.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(  // 전체 경로로 지정!
                    description = "사용자 질문 메시지",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = MessageRequest.class),
                            examples = @ExampleObject(value = "{\"message\": \"대한민국의 수도는 어디야?\"}")
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "ChatGPT 응답 성공",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = "{\"response\": \"대한민국의 수도는 서울입니다.\"}")
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "500", description = "서버 오류 또는 OpenAI 응답 오류")
            }
    )
    public ResponseEntity<Map<String, String>> askChat(@RequestBody MessageRequest request) {
        String message = request.getMessage();
        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException("Message cannot be null or empty");
        }

        String reply = chatGptService.getChatGptResponse(message);
        return ResponseEntity.ok(Map.of("response", reply));
    }
}