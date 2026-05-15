package com.example.pizzaria.Adaptadores.Apresentacao;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pizzaria.Aplicacao.AutenticarClienteUC;
import com.example.pizzaria.Aplicacao.RegistrarClienteUC;
import com.example.pizzaria.Aplicacao.Requests.AutenticarClienteRequest;
import com.example.pizzaria.Aplicacao.Requests.RegistrarClienteRequest;
import com.example.pizzaria.Aplicacao.Responses.AutenticarClienteResponse;
import com.example.pizzaria.Aplicacao.Responses.RegistrarClienteResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
@SecurityRequirement(name= "none")
@Tag(name = "Autenticação", description = "Operações relacionadas a autenticação e registro de usuários")
public class AuthController {

    private final RegistrarClienteUC registrarClienteUC;
    private final AutenticarClienteUC autenticarClienteUC;

    @PostMapping("/registrar")
    @Operation(
        summary = "Registrar novo cliente (UC1)",
        description = "Cria um novo cliente com email e senha. O cliente poderá utilizar essas credenciais para acessar endpoints autenticados.",
        security = {}
    )
    public ResponseEntity<RegistrarClienteResponse> registrar(@Valid @RequestBody RegistrarClienteRequest request) {
        RegistrarClienteResponse response = registrarClienteUC.run(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    @Operation(
        summary = "Autenticar cliente (UC2)",
        description = "Autentica o cliente no sistema usando email e senha. Retorna os dados do cliente autenticado.",
        security = {}
    )
    public ResponseEntity<AutenticarClienteResponse> login(@Valid @RequestBody AutenticarClienteRequest request) {
        AutenticarClienteResponse response = autenticarClienteUC.run(request);
        return ResponseEntity.ok(response);
    }
}
