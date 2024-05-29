package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@Slf4j
public class DownloadController {

    private final String FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\java\\com\\example\\demo\\file\\"; // 文件存储路径

    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> download(@PathVariable String filename)//定义一个名为download的公共方法，返回类型为ResponseEntity。该方法接受一个String类型的参数filename，表示请求中的文件名参数。
    {
        try {
            Path filePath = Paths.get(FILE_PATH + filename);
            log.info("打印uri:  {}", filePath.toUri());
            Resource resource = new UrlResource(filePath.toUri());//Resource resource = new UrlResource(filePath.toUri());
            if (!resource.exists()) //检查资源是否存在。如果不存在，抛出MalformedURLException异常。
            {
                throw new MalformedURLException("文件不存在");
            }
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);//如果资源存在，则返回HTTP状态码200（OK），并设置Content-Disposition头部，以便浏览器将文件作为附件下载。最后，将资源作为响应体返回。
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();//404
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();//500
        }
    }
    @GetMapping("/down/{filename}")
    public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable String filename) {
        try {
            Path filePath = Paths.get(FILE_PATH + filename);
            log.info("打印uri: {}", filePath.toUri());

            File file = filePath.toFile();
            log.info("打印file: {}",file);
            if (!file.exists()) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
            response.setContentLength((int) file.length());

            try (InputStream inputStream = new FileInputStream(file);
                 OutputStream outputStream = response.getOutputStream()) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                log.error("Error downloading file: {}", e.getMessage());
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            log.error("Error processing download request: {}", e.getMessage());
        }
    }

}




