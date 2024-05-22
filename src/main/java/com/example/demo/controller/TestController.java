package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
    @GetMapping("/ts1")
    public String aa(ModelAndView modelAndView){
        modelAndView.addObject("bb","abc");
        return "aa";
    }
}
