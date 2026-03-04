package br.com.senai.e_commerce.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "E-commerce API",
        version = "1.0",
        description = "API para o sistema ecommerce"
    ))
public class Swagger {
    
}
