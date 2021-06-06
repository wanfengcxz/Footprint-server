package com.footprint.image.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    /**
     * 保存图片到本地
     * @param imageFile 图片的二进制数据
     * @return 是否保存成功
     */
    public boolean saveImage(String userId, String articleId, MultipartFile imageFile, String index);

}
