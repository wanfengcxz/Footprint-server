package com.footprint.article.controller;

import com.footprint.article.service.ArticleService;
import com.footprint.eureka.entity.Article;
import com.footprint.eureka.utils.AESUtil;
import com.footprint.eureka.utils.Result;
import com.footprint.eureka.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/json")
public class ArticleController {

    @Autowired
    private ArticleService articleService = null;

    @RequestMapping(value = "/article", method = RequestMethod.POST)
    public Result myArticles(@RequestParam("user_id") String userId,
                             @RequestParam("encrypt_code") String encryptCode,
                             @RequestParam("title") String title,
                             @RequestParam("content") String content,
                             @RequestParam("timestamp") String timestamp,
                             @RequestParam("image_num") String image_num) {
        try {
            // 验证用户
            String id_decrypt = AESUtil.decode(encryptCode);
            if (id_decrypt.equals(userId)) {
                int res = articleService.publishArticle(userId,title,content,timestamp,image_num);
                System.out.println(res);
                if (res <= 0)
                    return ResultUtil.unknownError();
                else
                    return ResultUtil.success(res);
            } else
                return ResultUtil.illegalAccess();
        } catch (BadPaddingException e) {
            // 解密错误
            return ResultUtil.illegalAccess();
        }
    }

    @RequestMapping(value = "/my_articles", method = RequestMethod.GET)
    public Result myArticles(@RequestParam("user_id") String userId,
                               @RequestParam("encrypt_code") String encryptCode) {
        try {
            // 验证用户
            String id_decrypt = AESUtil.decode(encryptCode);
            if (id_decrypt.equals(userId)) {
                List<Article> articleList = articleService.getMyArticle(userId);
                return ResultUtil.success(articleList);
            } else
                return ResultUtil.illegalAccess();
        } catch (BadPaddingException e) {
            // 解密错误
            return ResultUtil.illegalAccess();
        }
    }

    @RequestMapping(value = "/random_articles", method = RequestMethod.GET)
    public Result randomArticles(@RequestParam("user_id") String userId,
                                 @RequestParam("encrypt_code") String encryptCode){
            try {
                // 验证用户
                String id_decrypt = AESUtil.decode(encryptCode);
                if (id_decrypt.equals(userId)) {
                    List<Article> articleList = articleService.getRandomArticles(userId,10);
                    return ResultUtil.success(articleList);
                }
                else
                    return ResultUtil.illegalAccess();

            } catch (BadPaddingException e) {
                // 解密错误
                return ResultUtil.illegalAccess();
            }
    }

    @RequestMapping(value = "/like", method = RequestMethod.POST)
    public Result like(@RequestParam("user_id") String userId,
                       @RequestParam("encrypt_code") String encryptCode,
                       @RequestParam("article_id") String articleId){
        try {
            // 验证用户
            String id_decrypt = AESUtil.decode(encryptCode);
            if (id_decrypt.equals(userId)) {
                System.out.println("this is controller like function");
                if (articleService.like(articleId,userId))
                    return ResultUtil.success();
                else
                    return ResultUtil.unknownError();
            }
            else
                return ResultUtil.illegalAccess();

        } catch (BadPaddingException e) {
            // 解密错误
            return ResultUtil.illegalAccess();
        }

    }
    @RequestMapping(value = "/dislike", method = RequestMethod.POST)
    public Result dislike(@RequestParam("user_id") String userId,
                       @RequestParam("encrypt_code") String encryptCode,
                       @RequestParam("article_id") String articleId) {
        try {
            // 验证用户
            String id_decrypt = AESUtil.decode(encryptCode);
            if (id_decrypt.equals(userId)) {
                if (articleService.dislike(articleId,userId))
                    return ResultUtil.success();
                else
                    return ResultUtil.unknownError();
            }
            else
                return ResultUtil.illegalAccess();

        } catch (BadPaddingException e) {
            // 解密错误
            return ResultUtil.illegalAccess();
        }
    }




}
