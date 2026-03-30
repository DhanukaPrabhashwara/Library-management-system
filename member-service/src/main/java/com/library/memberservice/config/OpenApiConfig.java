package com.library.memberservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for OpenAPI (Swagger) documentation.
 * Provides API information and customization for Swagger UI.
 */
@Configuration
public class OpenApiConfig {

    /**
     * Configure OpenAPI documentation.
     * Swagger UI will be available at: http://localhost:8081/swagger-ui.html
     *
     * @return OpenAPI configuration bean
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Member Service API")
                        .description("REST API for Library Management System - Member Service microservice")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Library Management System")
                                .url("http://localhost:8081")
                                .email("support@library.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
