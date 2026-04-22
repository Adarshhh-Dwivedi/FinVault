package com.example.SpringBank.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springBankOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SpringBank API")
                        .description("Modernized Bank API Definition")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Karan Sharma") // Updated to your preferred name
                                .url("https://github.com/your-profile")
                                .email("karan@example.com"))
                        .license(new License()
                                .name("MIT")
                                .url("http://springdoc.org")));
    }
}