package com.footprint.article.service;

import com.footprint.article.dao.ArticleDao;
import com.footprint.eureka.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao = null;

    @Override
    public List<Article> getMyArticle(String userId) {
        int userIdInt = Integer.parseInt(userId);
        Article article = new Article(-1, userIdInt, null, null, -1, -1, -1);
        return articleDao.selectArticles(article);
    }

    @Override
    public List<Article> getRandomArticles(int num) {
        Article article = new Article(-1, -1, null, null, -1, -1, -1);
        int article_num = articleDao.selectCount(article);
        if (article_num < num) {
            return articleDao.selectArticles(article);
        } else {
            int timeStamp = (int) (System.currentTimeMillis()/1000);
            return articleDao.getRandomArticle(timeStamp, num);
        }
    }
}
