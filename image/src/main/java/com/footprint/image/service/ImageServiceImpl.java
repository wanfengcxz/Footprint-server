package com.footprint.image.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService{

    @Override
    public boolean saveImage(String userId, String articleId, MultipartFile imageFile, String index) {
        if (imageFile.isEmpty())
            return false;

        String imageName = index + "png";
        String imagePath = "D:\\Coding\\tmp\\" + userId + "\\" + articleId + "\\";

        File dest = new File(imagePath + imageName);
        try {
            imageFile.transferTo(dest);
            return true;
        }catch (IOException e){
            return false;
        }
    }
}
