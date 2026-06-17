package com.example.entrega.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Entrega Service API")
                        .version("1.0")
                        .description("Microsservico de entregas da Pizzaria ECA - consome fila RabbitMQ e simula entregas"));
    }
}
