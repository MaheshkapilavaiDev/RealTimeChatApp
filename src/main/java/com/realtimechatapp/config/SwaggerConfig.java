package com.realtimechatapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI chatAPI(){

        return new OpenAPI()

                .info(

                        new Info()

                        .title("Real-Time Chat API")

                        .version("1.0")

                        .description("Spring Boot Real-Time Chat Application")

                );
    }

}
