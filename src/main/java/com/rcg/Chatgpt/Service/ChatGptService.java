package com.rcg.Chatgpt.Service;

import com.rcg.Chatgpt.Dto.Body;
import com.rcg.Chatgpt.Dto.ChatGptResponse;
import com.rcg.Chatgpt.Dto.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class ChatGptService {

    @Value("${openai.api.key}")
    private String key;

    public String getChatGptResponse(String messageContent) {
        if (messageContent == null || messageContent.trim().isEmpty()) {
            throw new IllegalArgumentException("Message content cannot be null or empty");
        }

        RestTemplate restTemplate = new RestTemplate();

        URI uri = UriComponentsBuilder
                .fromUriString("https://api.openai.com/v1/chat/completions")
                .build()
                .encode()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(key);

        List<Message> messages = List.of(new Message("user", messageContent));
        Body body = new Body("ft:gpt-3.5-turbo-1106:personal::9cGE81zd", messages); // ← 모델명 필요에 따라 수정

        RequestEntity<Body> request = new RequestEntity<>(body, headers, HttpMethod.POST, uri);

        ResponseEntity<ChatGptResponse> response = restTemplate.exchange(request, ChatGptResponse.class);

        ChatGptResponse chatGptResponse = response.getBody();
        if (chatGptResponse != null && !chatGptResponse.getChoices().isEmpty()) {
            return chatGptResponse.getChoices().get(0).getMessage().getContent();
        } else {
            throw new RuntimeException("Invalid response from OpenAI API");
        }
    }
}