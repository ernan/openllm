package com.fodala.controller;

import com.fodala.pojo.FileView;
import com.fodala.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class FileUploadController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    FileService serviceFactory;

    @Value("${file.root}")
    private String FILE_ROOT;

    @GetMapping("/listDirectories")
    public List<FileView> listDirectories(@RequestParam Optional<String> dir) {
        return serviceFactory.listDirectories(dir);
    }

    @GetMapping("/listFiles")
    public List<FileView> listFiles(@RequestParam Optional<String> dir) {
        return serviceFactory.listFiles(dir);
    }

    @GetMapping("/upload")
    public String listUploadedFiles(Model model) {
        model.addAttribute("files", serviceFactory.loadAll().map(
                        path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                                "serveFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList()));
        return "upload";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        serviceFactory.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        return "redirect:/upload";
    }

    @GetMapping("/deleteFile")
    public ResponseEntity<?> deleteFile(@RequestParam String filePath) {
        return ResponseEntity.ok(serviceFactory.delete(filePath));
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = serviceFactory.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

//    @GetMapping("/getFile/{fileName}")
//    public void getFile(@PathVariable String fileName, @RequestParam String filePath, HttpServletResponse response) {
//        File file = serviceFactory.getFile(filePath);
//        try (InputStream in = new FileInputStream(file)) {
//            response.setContentType(Files.probeContentType(file.toPath()));
//            response.getOutputStream().write(IOUtils.toByteArray(in));
//            response.flushBuffer();
//        } catch (Exception e) {
//            log.error("error occured getting the file. Exception is ---->", e);
//        }
//    }

    @GetMapping("/addFolder/{folderName}")
    public ResponseEntity<?> addFolder(@PathVariable String folderName, @RequestParam String folderPath) {
        return ResponseEntity.ok(serviceFactory.addFolder(folderName, folderPath));
    }

    @GetMapping("/deleteFolder/{folderName}")
    public ResponseEntity<?> deleteFolder(@PathVariable String folderName, @RequestParam String folderPath) {
        return ResponseEntity.ok(serviceFactory.deleteFolder(folderName, folderPath));
    }

    @GetMapping("/getFileRoot")
    public String getFileRoot() {
        return FILE_ROOT;
    }
}