package com.example.SpringBank.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springBankOpenAPI() {
        return new OpenAPI()
                .servers(List.of(new Server().url("http://localhost:8080").description("Development Server")))
                .info(new Info()
                        .title("SpringBank API")
                        .description("Modernized Bank Management System API built with Java 21 and Spring Boot 3.4.4")
                        .summary("This API handles core banking operations including account creation, fund transfers, and customer management.")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Karan Sharma")
                                .url("https://github.com/your-github-profile") // Update with your actual link
                                .email("karan.sharma@example.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")));
    }
}