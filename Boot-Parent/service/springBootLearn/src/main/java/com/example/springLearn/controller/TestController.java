package com.example.springLearn.controller;

import com.example.springLearn.pojo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Controller
public class TestController {
    private final String FILE_PATH = System.getProperty("user.dir") + "\\Boot-Parent\\service\\springBootLearn\\src\\main\\java\\com\\example\\springLearn\\file\\"; // 文件存储路径

    @GetMapping("/ts2")
    public ModelAndView aa() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("bb", "真不错");
        modelAndView.setViewName("aa");
        return modelAndView;
    }

    @GetMapping("/upload")
    public ModelAndView bb() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("bb", "真不错");
        modelAndView.setViewName("upload");
        return modelAndView;
    }

    //thymeleaf版本要在3.X以上
    @GetMapping("/ts3")
    public String cc(Model model) {
        model.addAttribute("bb", "zbc");
        return "aa";
    }

    @PostMapping("/upload")
    @ResponseBody
    public Result UploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("Please select a com.example.springLearn.file to upload.");
        }
        try {
//            System.out.println(System.getProperty("user.dir")+"\\src");
            String uploadDir = FILE_PATH;
            File uploadedFile = new File(uploadDir + file.getOriginalFilename());
            file.transferTo(uploadedFile);
            return Result.success("File uploaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("File upload failed!");
        }
    }

}
