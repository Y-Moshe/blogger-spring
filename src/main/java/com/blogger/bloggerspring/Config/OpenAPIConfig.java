package com.blogger.bloggerspring.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Blogger API Reference")
                        .description(
                                "An API Reference to all the supported endpoints to the restapi of the application")
                        .version("1.0"));
    }
}
