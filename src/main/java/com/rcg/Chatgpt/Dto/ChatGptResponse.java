package com.rcg.Chatgpt.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatGptResponse {
    private List<Choice> choices;
}