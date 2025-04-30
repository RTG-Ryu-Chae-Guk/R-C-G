package com.rcg.news.Dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public  class NaverNewsResponse {
    private String lastBuildDate; // 새로운 필드 추가

    private List<NaverNewsItem> items;

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public List<NaverNewsItem> getItems() {
        return items;
    }

    public void setItems(List<NaverNewsItem> items) {
        this.items = items;
    }
}