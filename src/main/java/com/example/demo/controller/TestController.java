package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
    @GetMapping("/ts2")
    public ModelAndView aa(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("bb","真不错");
        modelAndView.setViewName("aa");
        return modelAndView;
    }
    @GetMapping("/ts3")
    public String cc(Model model){
        model.addAttribute("bb","zbc");
        return "aa";
    }
}
