package com.fodala.service;

import com.fodala.pojo.AjaxResponse;
import com.fodala.pojo.FileView;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


public interface FileService {

    List<FileView> listDirectories(Optional<String> dir);

    List<FileView> listFiles(Optional<String> dir);

    File getFile(String filePath);

    AjaxResponse upload(MultipartFile file, Optional<String> dir, Optional<String> type);

    AjaxResponse delete(String filePath);

    AjaxResponse addFolder(String folderName, String folderPath);

    AjaxResponse deleteFolder(String folderName, String folderPath);

    String getFileSeparator();

    Resource loadAsResource(String fileName);

    Path load(String filename);

    Stream<Path> loadAll();

    void store(MultipartFile file);
}
