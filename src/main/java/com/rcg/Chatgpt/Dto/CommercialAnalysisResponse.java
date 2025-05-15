package com.rcg.Chatgpt.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommercialAnalysisResponse {
    private String analysis;
    private String recommendation;
}