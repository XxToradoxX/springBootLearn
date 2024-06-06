//package com.example.springLearn.config;
//
//
//import com.example.common.interceptor.MyInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@ComponentScan("com.example")
//public class SpringmvcConfig implements WebMvcConfigurer {
//    @Autowired
//    private MyInterceptor interceptor;
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
////        WebMvcConfigurer.super.addViewControllers(registry);
//        registry.addViewController("/").setViewName("forward:nb.html");
//        registry.addViewController("/ts1").setViewName("aa");
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(interceptor).addPathPatterns("/ts1");
//    }
//}
