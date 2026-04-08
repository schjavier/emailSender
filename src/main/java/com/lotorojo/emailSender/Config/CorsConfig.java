package com.lotorojo.emailSender.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {


        @Override
        public void addCorsMappings(CorsRegistry registry){
            registry.addMapping("/**")
                    .allowedOrigins(
                            "https://payasomarote.com",
                            "https://payasomarote.com/es/",
                            "https://payasomarote.com/en/",
                            "https://www.payasomarote.com",
                            "https://www.payasomarote.com/es/",
                            "https://www.payasomarote.com/en/",
                            "https://www.lotorojo.com.ar",
                            "https://lotorojo.com.ar"
                            )
                    .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                    .allowedHeaders("*")
                    .allowCredentials(true);
        }

    }
