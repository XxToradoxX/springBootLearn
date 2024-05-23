package com.example.demo.config;


import com.example.demo.servletComponent.MyServlet;
import org.springframework.beans.factory.annotation.Autowired;
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
        ServletRegistrationBean<MyServlet> bean = new ServletRegistrationBean<>(myServlet,"/byd1");
        bean.setUrlMappings(arr);
        return bean;
    }
}
