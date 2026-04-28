package com.example.personal_finance_tracker_api.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;

// 👉 ADD THESE IMPORTS
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(apiInfo())

                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                );
    }

    private Info apiInfo(){
        return new Info()
                .title("Personal-Finance-Tracker-API")
                .description("API documentation for Tracking")
                .version("1.0.0")
                .contact(apiContactDetails())
                .license(apiLicense());
    }

    private Contact apiContactDetails(){
        return new Contact()
                .name("Mohammad Hasibul Hasan")
                .email("hasibulx26@gmail.com");
    }

    private License apiLicense(){
        return new License()
                .name("MIT License")
                .url("https://opensource.org/licenses/MIT");
    }
}