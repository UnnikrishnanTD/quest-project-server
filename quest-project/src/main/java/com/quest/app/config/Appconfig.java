package com.quest.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class Appconfig implements WebMvcConfigurer{
	
	@Value("${app.cors.allowedOrigins}")
	private String corsOrigins;

	@Override
    public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")  // Applies CORS settings to all routes, including subpaths
        .allowedOrigins(corsOrigins)  // Use the origins from properties
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allow specific methods
        .allowedHeaders("*")  // Allow all headers
        .allowCredentials(true); 
    }
}
