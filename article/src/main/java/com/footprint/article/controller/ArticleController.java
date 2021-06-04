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
                    List<Article> articleList = articleService.getRandomArticles(3);
                    return ResultUtil.success(articleList);
                }
                else
                    return ResultUtil.illegalAccess();

            } catch (BadPaddingException e) {
                // 解密错误
                return ResultUtil.illegalAccess();
            }

    }
}
