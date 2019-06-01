package com.elearning.program.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan("com.elearning.program")
public class WebMvcConfig implements WebMvcConfigurer {
  public void configureViewResolvers(ViewResolverRegistry viewResolverRegistry) {
    viewResolverRegistry.jsp("/WEB-INF/views/", ".jsp");
  }
}
