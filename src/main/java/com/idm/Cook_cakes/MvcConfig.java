package com.idm.Cook_cakes;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableCaching
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Настройка ResourceHandler для обслуживания ресурсов из папки resources/images
        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/images/");
    }
}