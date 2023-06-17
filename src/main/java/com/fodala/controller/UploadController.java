package com.fodala.controller;

import com.fodala.mapper.UserMapper;
import com.fodala.pojo.User;
import com.fodala.pojo.UserImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UploadController {
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

    @Autowired
    UserMapper userMapper;

    @PostMapping("/uploadUserImage")
    public String uploadImage(Model model, @RequestParam("id") Integer id, @RequestParam("image") MultipartFile file) throws IOException {
        StringBuilder fileNames = new StringBuilder();
        if (file.getOriginalFilename().length() == 0) {
            model.addAttribute("message", "Please select a file to upload");
        } else {
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
            File uploadDirectory = new File(UPLOAD_DIRECTORY);
            boolean uploadDirectoryExists = uploadDirectory.exists();
            if (!uploadDirectoryExists) {
                logger.warn("{} not found creating directory", uploadDirectory);
                uploadDirectoryExists = uploadDirectory.mkdirs();
                logger.warn("{} created {}", uploadDirectory, uploadDirectoryExists);
            }
            fileNames.append(file.getOriginalFilename());
            Files.write(fileNameAndPath, file.getBytes());
            UserImage userImage = new UserImage(id, file.getBytes());
            userMapper.insertImage(userImage);
            model.addAttribute("message", "Uploaded images: " + fileNames.toString());
        }
        if (id != null) {
            User user = userMapper.findById(id);
            model.addAttribute("history", userMapper.history(id));
            model.addAttribute("user", user);
        }
        return "user/edit";
    }
}
