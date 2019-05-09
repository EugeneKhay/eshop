package com.eshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//public class WebConfig extends WebMvcConfigurerAdapter {

@Configuration
@ComponentScan("com.eshop")
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("resourses/static/js/**")
                .addResourceLocations("resourses/static/js/");
        registry.addResourceHandler("resourses/static/css/**")
                .addResourceLocations("resourses/static/css/");
        registry.addResourceHandler("resourses/static/views/**")
                .addResourceLocations("resourses/static/views/");
        registry.addResourceHandler("resourses/static/**")
                .addResourceLocations("resourses/static/");
        registry.addResourceHandler("webjars/**")
                .addResourceLocations("/webjars/");
    }

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
