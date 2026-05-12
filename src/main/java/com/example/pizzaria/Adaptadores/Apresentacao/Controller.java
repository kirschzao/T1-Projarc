package com.example.pizzaria.Adaptadores.Apresentacao;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Health Check", description = "Operação para consultar status da aplicação")

public class Controller {
    @GetMapping("")
    @Operation(
        summary = "Healthy Check", 
        description = "Retorna a docs do Scalar"
    )
    @CrossOrigin("*")
    public String welcomeMessage() {
        return "Bem Vindo a Pizzaria ECA";
    }
}
