package com.rcg.news.Dto;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
@Setter
@ToString
public class NewsDTO {


    private String title;
    private String link;
    private String description;
    private String pubDate;

}