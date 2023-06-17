package com.fodala.service.impl;

import com.fodala.exception.StorageException;
import com.fodala.exception.StorageFileNotFoundException;
import com.fodala.pojo.AjaxResponse;
import com.fodala.pojo.FileView;
import com.fodala.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class FileServiceImpl implements FileService {
    static final String PATH_SEPARATOR = "/";
    Logger logger = LoggerFactory.getLogger(getClass());
    @Value("${file.directory}")
    private String FILE_DIRECTORY;
    @Value("${file.root}")
    private String FILE_ROOT;
    @Value(value = "${file.upload.directory:#{.}}")
    private Path rootLocation;

    @Override
    public List<FileView> listDirectories(Optional<String> dir) {
        List<FileView> files = new ArrayList<>();
        try {
            File root = new File(getFilePath(dir));
            if (root.exists()) {
                files = Arrays.asList(root.listFiles()).stream().filter(file -> file.isDirectory())
                        .map(file -> new FileView(file.getName(), true, getFileViewPath(dir), PATH_SEPARATOR))
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
            logger.error("error occured. exception is ---->", e);
        }
        return files;
    }

    @Override
    public List<FileView> listFiles(Optional<String> dir) {
        List<FileView> files = new ArrayList<>();
        try {
            File root = new File(getFilePath(dir));
            if (root.exists() & root.isDirectory()) {
                files = Arrays.stream(Objects.requireNonNull(root.listFiles())).filter(file -> !file.isDirectory())
                        .filter(file -> !file.getName().contains(".db"))
                        .map(file -> new FileView(file.getName(), false, getFileViewPath(dir), PATH_SEPARATOR))
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
            logger.error("error occured. exception is ---->", e);
        }
        return files;
    }

    @Override
    public File getFile(String filePath) {
        return new File(getFilePath(Optional.of(filePath)));
    }

    @Override
    public AjaxResponse upload(MultipartFile file, Optional<String> dir, Optional<String> type) {
        AjaxResponse response = new AjaxResponse();
        String fileName = file.getOriginalFilename();
        if (Objects.requireNonNull(fileName).contains("\\"))
            fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
        try {
            File toUpload = new File(getFilePath(dir, type) + PATH_SEPARATOR + fileName);
            if (toUpload.exists()) {
                String message = "a file by that name already exists";
                response.setError(new AjaxResponse.Error(message));
                logger.error(message);
            } else {
                writeByteArrayToFile(file.getBytes(), toUpload);
                response.setFileName(fileName);
                response.setUploaded(true);
                response.setMessage("File uploaded successfully!");
            }
        } catch (Exception e) {
            String message = "unable to upload file::" + fileName + " at this time.";
            response.setError(new AjaxResponse.Error(message));
            logger.error(message.concat("Exception is---->"), e);
        }
        return response;
    }

    @Override
    public AjaxResponse delete(String filePath) {
        AjaxResponse response = new AjaxResponse();
        try {
            File file = new File(getFilePath(Optional.of(filePath)));
            if (!file.delete()) {
                String message = "Error Deleting the File.";
                response.setError(new AjaxResponse.Error(message));
                logger.error(message);
            } else
                response.setMessage("File Deleted Successfully.");
        } catch (Exception e) {
            String message = "Error Deleting the File.";
            response.setError(new AjaxResponse.Error(message));
            logger.error(message.concat("Exception is---->"), e);
        }
        return response;
    }

    @Override
    public AjaxResponse addFolder(String folderName, String folderPath) {
        AjaxResponse response = new AjaxResponse();
        File dir = null;
        try {
            if (isNotEmpty(folderPath))
                dir = getFile(folderPath + PATH_SEPARATOR + folderName);
            else
                dir = getFile(folderName);
            if (dir.exists() && dir.isDirectory()) {
                String message = "A folder with the name " + folderName + " already exists";
                response.setError(new AjaxResponse.Error(message));
                logger.error(message);
            } else if (dir.mkdirs()) {
                response.setMessage("Folder created successfully!");
            } else
                throw new Exception("Couldn't add folder.");
        } catch (Exception e) {
            String message = "Error adding folder " + folderName + " at this time";
            response.setError(new AjaxResponse.Error(message));
            logger.error(message.concat("Exception is---->"), e);
        }
        return response;
    }

    @Override
    public AjaxResponse deleteFolder(String folderName, String folderPath) {
        AjaxResponse response = new AjaxResponse();
        File dir = null;
        try {
            if (isNotEmpty(folderPath))
                dir = getFile(folderPath + PATH_SEPARATOR + folderName);
            else
                dir = getFile(folderName);
            if (!dir.exists() && dir.isDirectory()) {
                String message = "A folder with the name " + folderName + " doesn't exist!";
                response.setError(new AjaxResponse.Error(message));
                logger.error(message);
            } else if (Objects.requireNonNull(dir.list()).length > 0) {
                String message = "The specified folder is not empty!";
                response.setError(new AjaxResponse.Error(message));
                logger.error(message);
            } else if (dir.delete())
                response.setMessage("Folder deleted successfully!");
            else
                throw new Exception("Couldn't delete folder.");
        } catch (Exception e) {
            String message = "Error deleting folder " + folderName + " at this time!";
            response.setError(new AjaxResponse.Error(message));
            logger.error(message.concat("Exception is---->"), e);
        }
        return response;
    }

    @SafeVarargs
    private final String getFilePath(Optional<String>... dirs) {
        StringBuilder base = new StringBuilder(FILE_DIRECTORY + PATH_SEPARATOR + FILE_ROOT);
        for (Optional<String> dir : dirs) {
            if (dir.isPresent() && isNotEmpty(dir.get()))
                base.append(PATH_SEPARATOR).append(stripFileRootFromPath(dir.get()));
        }
        return base.toString();
    }

    @SafeVarargs
    private final String getFileViewPath(Optional<String>... dirs) {
        StringBuilder base = new StringBuilder(FILE_ROOT);
        for (Optional<String> dir : dirs) {
            if (dir.isPresent() && isNotEmpty(dir.get()))
                base.append(PATH_SEPARATOR).append(stripFileRootFromPath(dir.get()));
        }
        return base.toString();
    }

    private String stripFileRootFromPath(String path) {
        String strippedPath = path;
        if (path.contains(FILE_ROOT.concat(PATH_SEPARATOR)))
            strippedPath = path.replace(FILE_ROOT.concat(PATH_SEPARATOR), "");
        else if (path.contains(FILE_ROOT))
            strippedPath = path.replace(FILE_ROOT, "");
        return strippedPath;
    }


    boolean isNotEmpty(String data) {
        return data != null && data.length() > 0;
    }

    void writeByteArrayToFile(byte[] data, File outputFile) {
        try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
            outputStream.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getFileSeparator() {
        return PATH_SEPARATOR;
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public void store(MultipartFile file) {
        try {
            logger.info("Storing file: {}", file);
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            Path destinationFile = this.rootLocation.resolve(
                            Paths.get(Objects.requireNonNull(file.getOriginalFilename())))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }


    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }
}