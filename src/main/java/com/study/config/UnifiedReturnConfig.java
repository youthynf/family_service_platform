package com.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class UnifiedReturnConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        /**
         * 注册
         */
        registry.addConverter(new LocalDateTimeConverter.StringToLocalDateTimeConverter());
    }
}
