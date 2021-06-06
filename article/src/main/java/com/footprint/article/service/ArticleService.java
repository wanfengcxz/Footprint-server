package com.footprint.article.service;

import com.footprint.eureka.entity.Article;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ArticleService {

    /**
     * 插入文章
     * @param userId 用户ID
     * @param title 文章标题
     * @param content 文章内容
     * @param timestamp 时间戳
     * @param image_num 照片数目
     * @return 1插入成功
     */
    public int publishArticle(String userId, String title, String content, String timestamp, String image_num);

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
    public List<Article> getRandomArticles(String userId, int num);

    /**
     * 用户点赞
     * @param articleId 被点赞文章的ID
     * @param userId 点赞用户的ID
     * @return 点赞是否成功
     */
    public boolean like(String articleId, String userId);

    /**
     * 用户取消点赞
     * @param articleId 取消点赞的文章ID
     * @param userId 取消点赞用户的ID
     * @return 取消点赞是否成功
     */
    public boolean dislike(String articleId, String userId);
}
