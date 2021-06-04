package com.footprint.article.service;

import com.footprint.eureka.entity.Article;

import java.util.List;

public interface ArticleService {

    /**
     * 获取当前用户的所有文章
     * @param userId 用户id
     * @return 文章列表
     */
    public List<Article> getMyArticle(String userId);

    /**
     * 随机获取一些文章
     * 文章数量不足时返回全部文章
     * @return 文章列表
     */
    public List<Article> getRandomArticles(int num);

}
