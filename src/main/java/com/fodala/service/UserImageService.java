package com.fodala.service;

public interface UserImageService {

    Integer saveImage(String tableName, Integer userId, byte[] data);

    Integer updateImage(byte[] data, String tableName, Integer id);

    byte[] readImage(Integer id, String tableName);
}
