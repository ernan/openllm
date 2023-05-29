package com.fodala.pojo;

import com.fodala.controller.UploadController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;

public class UserImage {
    private static final Logger logger = LoggerFactory.getLogger(UserImage.class);

    Integer userId;
    String photo;

    public UserImage() {

    }

    public UserImage(Integer userId, byte[] data) {
        this.userId = userId;
        this.photo = Base64.getEncoder().encodeToString(data);;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhoto() {
        if (photo != null) {
            logger.info("Returning binary data of length: {}", photo.length());
        }
        return photo;
    }

    public void setPhoto(byte[] data) {
        this.photo = Base64.getEncoder().encodeToString(data);
        logger.info("Saved binary data of length: {}", photo.length());
    }
}
