package com.example.springLearn.controller;

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
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@Slf4j
public class DownloadController {

    private final String FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\java\\com\\example\\springLean\\com.example.springLearn.file\\"; // 文件存储路径

    @GetMapping("/download/{filename}")
//    返回类型是ResponseEntity<Resource>,表示这个方法返回一个包含文件资源的HTTP响应实体。
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
                    .body(resource);
//            如果文件资源存在,则创建一个ResponseEntity对象作为响应。
//            设置HTTP状态码为200(OK)。
//            添加一个"Content-Disposition"响应头,告诉浏览器以附件形式下载该文件。
//            将文件资源(Resource对象)设置为响应体。
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();//404
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();//500
        }
    }
    @GetMapping("/down/{filename}")
    public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable String filename) {
        try {
            Path filePath = Paths.get(FILE_PATH + filename);//获得完整路径
            log.info("打印uri: {}", filePath.toUri());

            File file = filePath.toFile();//将路径转换为File对象
            log.info("打印file: {}",file);
            if (!file.exists()) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            response.setContentType("application/octet-stream");//设置响应的Content-Type为"application/octet-stream",表示这是一个二进制流文件。
            response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");//设置响应头"Content-Disposition"为"attachment; filename="..."",告诉浏览器以附件形式下载该文件。
            response.setContentLength((int) file.length());
//            创建文件输入流和响应输出流。
//            将文件内容从输入流逐步写入到输出流中,完成文件下载。
            try (InputStream inputStream = new FileInputStream(file);
                 OutputStream outputStream = response.getOutputStream()) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                log.error("Error downloading com.example.springLearn.file: {}", e.getMessage());
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            log.error("Error processing download request: {}", e.getMessage());
        }
    }
//    Path和File是Java中处理文件系统相关操作的两个重要对象:
//
//    Path:
//
//    Path是Java 7 引入的一个新的文件路径抽象类,位于java.nio.file包下。
//    Path对象表示文件系统中的一个路径,可以是文件或目录。它提供了一种更加面向对象和跨平台的方式来处理文件路径。
//            Path对象可以通过Paths.get(String first, String... more)方法创建,该方法会根据系统平台自动适配路径分隔符(Windows使用"",Unix使用"/")。
//    Path提供了丰富的API,可以用于路径拼接、规范化、比较等操作。
//    File:
//
//    File是Java中较早引入的一个类,位于java.io包下。
//    File对象表示文件系统中的一个文件或目录。
//    File对象可以通过文件路径字符串创建,例如new File("/path/to/com.example.springLearn.file.txt")。
//    File提供了一些常见的文件操作方法,如exists()、delete()、length()等。
//    在上面的代码中:
//
//            使用Paths.get(FILE_PATH + filename)创建了一个Path对象,表示要下载的文件的完整路径。
//            然后使用filePath.toFile()将Path转换为File对象,以便进行一些文件操作,如检查文件是否存在、获取文件长度等。
//    总的来说,Path和File都是Java处理文件系统的重要工具,Path提供了更加面向对象和跨平台的API,而File则保留了较早版本的一些常见文件操作方法。在实际开发中,根据具体需求选择使用哪个对象都是很常见的。





//    Resource是Spring框架提供的一个接口,用于表示各种类型的资源,包括文件系统资源、网络资源、类路径资源等。它位于org.springframework.core.io包中。
//
//    Resource接口的主要作用是:
//
//    统一资源访问接口:
//
//    Resource接口为不同类型的资源(如文件、URL、ClassPath资源等)提供了一个统一的访问接口,使得开发人员可以用相同的方式访问不同来源的资源。
//    获取资源信息:
//
//    Resource接口提供了一些方法,如getInputStream()、getFile()、exists()等,用于获取资源的输入流、文件对象,以及判断资源是否存在等。
//    资源路径解析:
//
//    Resource接口提供了getURL()、getURI()等方法,用于获取资源的URL或URI表示。这在处理不同类型资源的路径时很有帮助。
//    在上面的代码中,我们使用了UrlResource类,它是Resource接口的一个实现,用于表示文件系统资源。
//
//    具体来说:
//
//            new UrlResource(filePath.toUri()) 创建了一个UrlResource对象,表示要下载的文件资源。
//            通过调用resource.exists()方法,我们可以检查资源是否存在。
//    最后,将resource对象直接作为响应体返回,Spring会自动处理资源的读取和传输。
//    使用Resource接口可以让我们的代码更加通用和可维护,因为它提供了一个统一的资源访问抽象,而不需要关心具体的资源类型。这在处理不同来源的资源时非常有用。


//    Spring 使用 ResponseEntity 来处理 I/O 流的过程如下:
//
//    创建 Resource 对象:
//
//    Spring 提供了 Resource 接口及其各种实现类,如 UrlResource、ClassPathResource 等,用于表示不同类型的资源。
//    在示例中,我们使用 UrlResource 来表示文件系统资源。
//    读取资源内容:
//
//    通过 resource.getInputStream() 方法可以获取资源的输入流。
//    这个输入流可以用于读取资源的内容,比如文件的字节数据。
//    构建 ResponseEntity:
//
//    ResponseEntity 是 Spring 提供的一个类,用于构建完整的 HTTP 响应。
//    在示例中,我们使用 ResponseEntity.ok() 方法创建一个初始的 ResponseEntity 对象,表示 HTTP 200 OK 响应。
//    然后通过链式调用的方式设置响应头和响应体:
//    header(HttpHeaders.CONTENT_DISPOSITION, ...) 设置 Content-Disposition 响应头。
//    body(resource) 将资源对象设置为响应体。
//    资源管理:
//
//    当使用 ResponseEntity 时,Spring 会负责管理资源的生命周期。
//    也就是说,当响应被发送给客户端后,Spring 会自动关闭 resource.getInputStream() 返回的输入流。
//    这避免了手动管理资源的打开和关闭,减少了潜在的资源泄漏问题。
//    异常处理:
//
//    在示例中,我们捕获了 MalformedURLException 和 IOException 异常。
//    对于文件不存在的情况,我们返回 HTTP 404 Not Found 响应。
//    对于其他异常,我们返回 HTTP 500 Internal Server Error 响应。
//    这种异常处理方式简洁明了,不需要手动设置响应状态码。
//    总的来说,Spring 的 ResponseEntity 通过封装 I/O 流的读取和 HTTP 响应的构建,使得代码更加简洁、易读和易于维护。同时,它还能够自动管理资源的生命周期,并提供更好的异常处理机制。这些特性使得 ResponseEntity 成为 Spring Web 应用程序中处理文件下载等场景的首选方式。
}




