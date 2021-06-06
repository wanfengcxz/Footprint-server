package com.footprint.image.controller;

import com.footprint.eureka.utils.AESUtil;
import com.footprint.eureka.utils.Result;
import com.footprint.eureka.utils.ResultUtil;
import com.footprint.image.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.crypto.BadPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;


@RestController
@CrossOrigin()
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService imageService = null;

    @RequestMapping(value = "/article_image", method = RequestMethod.POST)
    public Result uploadArticleImage(HttpServletRequest request, HttpServletResponse response) {
        try {
            //设置编码
            request.setCharacterEncoding("utf-8");

            StandardMultipartHttpServletRequest req = (StandardMultipartHttpServletRequest) request;
            //获取formdata的值
            Iterator<String> iterator = req.getFileNames();
            String encryptCode = request.getParameter("encrypt_code");
            String userId = request.getParameter("user_id");
            String articleId = request.getParameter("article_id");
            String index = request.getParameter("index");
//            System.out.println(userId);

            // 验证用户
            String id_decrypt = AESUtil.decode(encryptCode);
            if (id_decrypt.equals(userId)) {
                while (iterator.hasNext()) {
                    MultipartFile file = req.getFile(iterator.next());
                    String imageName = index + ".png";
//                    String imagePath = "D:\\Coding\\tmp\\" + userId + "\\" + articleId + "\\";
                    String imagePath = "/home/ubuntu/footprint/image" + userId + "/" + articleId + "/";
                    File dir = new File(imagePath);
                    if (!dir.isDirectory())
                        dir.mkdirs();
                    //真正写到磁盘上
                    File file1 = new File(imagePath + imageName);
                    OutputStream out = new FileOutputStream(file1);
                    out.write(file.getBytes());
                    out.close();
                }
                return ResultUtil.success();
            } else
                return ResultUtil.illegalAccess();
        } catch (BadPaddingException | UnsupportedEncodingException | FileNotFoundException e) {
            // 解密错误
            return ResultUtil.illegalAccess();
        } catch (IOException e) {
            e.printStackTrace();
            return ResultUtil.illegalAccess();
        }
    }


}
