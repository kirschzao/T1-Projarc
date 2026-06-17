package com.example.pizzaria.Adaptadores.Apresentacao;

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
                        .title("Pizzaria ECA - API Principal")
                        .version("1.0")
                        .description("Microsservico principal da Pizzaria ECA - gestao de pedidos, cardapios, clientes, descontos e impostos"));
    }
}
