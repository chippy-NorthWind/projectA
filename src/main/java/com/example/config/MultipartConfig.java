package com.example.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * @author 橙鼠鼠
 */
@Configuration
public class MultipartConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory=new MultipartConfigFactory();
        DataSize maxSize=DataSize.ofMegabytes(10);
        DataSize requestMaxSize=DataSize.ofMegabytes(30);
        factory.setMaxFileSize(maxSize);
        factory.setMaxRequestSize(requestMaxSize);
        return factory.createMultipartConfig();
    }
}
