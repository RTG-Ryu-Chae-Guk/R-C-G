package com.rcg.commercialData.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TopStoreCategoryDto {
    private String name;
    private Integer storeCo;
    private Double closeRt;
}