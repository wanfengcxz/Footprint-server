package com.footprint.article.service;

import com.footprint.article.dao.ArticleDao;
import com.footprint.eureka.entity.Article;
import com.footprint.eureka.entity.Like;
import com.footprint.eureka.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao = null;


    @Override
    public int publishArticle(String userId, String title, String content, String timestamp, String image_num) {
        int userIdInt = Integer.parseInt(userId);
        int timeStampInt = Integer.parseInt(timestamp);
        int imageNumInt = Integer.parseInt(image_num);
        Article article = new Article(0,userIdInt,title,content,-1,timeStampInt,imageNumInt,0,null);
        return articleDao.insertArticle(article);
    }

    @Override
    public List<Article> getMyArticle(String userId) {
        int userIdInt = Integer.parseInt(userId);
        Article article = new Article(-1, userIdInt, null, null, -1, -1, -1, -1,null);
        List<Article> articleList = articleDao.selectArticles(article);
        if (articleList != null) {
            Like like = new Like();
            for(Article article1:articleList){
                // 获取点赞信息
                like.setArticleId(article1.getArticleId());
                like.setUserId(userIdInt);
                int isLike = articleDao.selectLikeCount(like);
                article1.setIsLike(isLike);
            }
            return articleList;
        } else
            return null;
    }

    @Override
    public List<Article> getRandomArticles(String userId, int num) {
        int userIdInt = Integer.parseInt(userId);
        int timeStamp = (int) (System.currentTimeMillis() / 1000);
        List<Article> articleList = articleDao.getRandomArticle(timeStamp, num);
        if (articleList != null) {
            User user = new User();
            Like like = new Like();
            for(Article article1:articleList){
                // 获取点赞信息
                like.setArticleId(article1.getArticleId());
                like.setUserId(userIdInt);
                int isLike = articleDao.selectLikeCount(like);
                article1.setIsLike(isLike);
                // 获取用户名
                user.setUserId(article1.getUserId());
                System.out.println("userId"+user.getUserId());
                User user2 = articleDao.selectUser(user);
                System.out.println(user2);
                article1.setUserName(user2.getUserName());
            }
            return articleList;
        } else
            return null;
    }

    @Override
    public boolean like(String articleId, String userId) {
        // 构造查询条件，首先判断文章是否存在
        Article article = new Article(Integer.parseInt(articleId),-1,null,null,-1,-1,-1,-1,null);
        // 如果数据库不存在这篇文章 则错误
        if (articleDao.selectArticleCount(article) != 1) return false;
        Like like = new Like(0,Integer.parseInt(articleId),Integer.parseInt(userId));
        int like_num = articleDao.selectLikeCount(like);
        // 当前数据库没有点赞记录
        if (like_num == 0){
            // 插入当前点赞记录
            int res1 = articleDao.insertLike(like);
            // 同时该文章点赞数加1
            int res2 = articleDao.TotalLikePlusOne(like.getArticleId());
            return res1 == 1 && res2 == 1;
        }
        return false;
    }

    @Override
    public boolean dislike(String articleId, String userId) {
        // 构造查询条件，首先判断文章是否存在
        Article article = new Article(Integer.parseInt(articleId),-1,null,null,-1,-1,-1,-1,null);
        // 如果数据库不存在这篇文章 则错误
        if (articleDao.selectArticleCount(article) != 1) return false;
        Like like = new Like(0,Integer.parseInt(articleId),Integer.parseInt(userId));
        int like_num = articleDao.selectLikeCount(like);
        System.out.println(like_num);
        // 当前数据库有点赞记录
        if (like_num == 1){
            // 删除当前点赞记录
            int res1 = articleDao.deleteLike(like);
            // 同时该文章点赞数减1
            int res2 = articleDao.TotalLikeMinusOne(like.getArticleId());
            System.out.println(res1);
            System.out.println(res2);
            return res1 == 1;
        }
        return false;
    }
}
