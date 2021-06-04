package com.footprint.article.dao;

import com.footprint.eureka.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ArticleDao {

    /**
     * 新增文章
     * @param article 文章brean
     * @return 影响行数
     */
    public int insertArticle(Article article);

    /**
     * 根据指定条件查找文章
     * @param article 条件
     * @return 文章列表
     */
    public List<Article> selectArticles(Article article);

    /**
     * 统计指定条件文章的数量
     * @param article 条件
     * @return 文章数量
     */
    public int selectCount(Article article);

    /**
     * 随机获取一些文章
     * @param num 数量
     * @return 文章列表
     */
    public List<Article> getRandomArticle(int timeStamp, int num);
}
