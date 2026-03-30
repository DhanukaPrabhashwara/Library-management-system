package com.library.borrowservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI borrowServiceOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Borrow Service API")
                        .description("API for managing book borrowing and returning")
                        .version("v1"));
    }
}
