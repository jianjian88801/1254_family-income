package com.xunmaw.income.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class RequestInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    RequestInterceptor requestInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInterceptor).excludePathPatterns("/login");
    }
}
