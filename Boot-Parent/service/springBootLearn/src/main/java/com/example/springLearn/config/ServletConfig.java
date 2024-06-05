package com.example.springLearn.config;


import com.example.springLearn.servletComponent.MyServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class ServletConfig {
    @Bean
    public ServletRegistrationBean<MyServlet> getServlet(MyServlet myServlet){
        ArrayList<String> arr = new ArrayList<>();
        arr.add("/byd");
        arr.add("/byd1");
        ServletRegistrationBean<MyServlet> bean = new ServletRegistrationBean<>(myServlet);
        bean.setUrlMappings(arr);
        return bean;
    }
}
