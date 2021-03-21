package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * 保存的图片能立即刷新
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/film/img/**").
       // addResourceLocations("file:F:\\spring-boot-project-test\\XhApp\\src\\main\\resources\\static\\film\\img\\");
        addResourceLocations("file:D:\\work\\xhapp\\xhapp\\src\\main\\resources\\static\\film\\img\\");
        
    }
}
