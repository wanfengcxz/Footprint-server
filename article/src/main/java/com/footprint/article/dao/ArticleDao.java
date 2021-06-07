package com.footprint.article.dao;

import com.footprint.eureka.entity.Article;
import com.footprint.eureka.entity.Like;
import com.footprint.eureka.entity.User;
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
    public int selectArticleCount(Article article);

    /**
     * 随机获取一些文章
     * @param num 数量
     * @return 文章列表
     */
    public List<Article> getRandomArticle(int timeStamp, int num);

    /**
     * 将指定文章点赞数加1
     * @param articleId 文章ID
     * @return 1:点赞数减1成功
     */
    public int TotalLikePlusOne(int articleId);

    /**
     * 将指定文章点赞数加1
     * @param articleId 文章ID
     * @return 1:点赞数加1成功
     */
    public int TotalLikeMinusOne(int articleId);

    /**
     * 查询是否有点赞记录
     * @param like 点赞记录(用户ID，文章ID)
     * @return 1:有 0:没有
     */
    public int selectLikeCount(Like like);

    /**
     * 删除点赞记录
     * @param like 点赞记录
     * @return 1:删除成功 0:没有当前记录
     */
    public int deleteLike(Like like);

    /**
     * 插入点赞记录
     * @param like 点赞记录
     * @return 插入结果
     */
    public int insertLike(Like like);

    /**
     * 根据条件查询单个用户
     * @param user 用户Bean
     * @return 查找到的用户
     */
    public User selectUser(User user);


}
