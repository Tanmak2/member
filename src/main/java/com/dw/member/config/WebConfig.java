package com.dw.member.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    Interceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] patterns = { "/login", "/error", "/resoures/static/*", "/api/v1/login" };
        registry.addInterceptor(interceptor).excludePathPatterns(patterns);
    }
}
