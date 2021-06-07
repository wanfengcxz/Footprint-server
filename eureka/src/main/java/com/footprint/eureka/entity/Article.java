package com.footprint.eureka.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    private int articleId;

    private int userId;

    private String title;

    private String content;

    private int totalLike;

    private int timeStamp;

    private int imageNum;

    private int isLike;

    private String userName;

}
