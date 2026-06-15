package com.example.pizzaria.Adaptadores.Apresentacao;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/internal/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RegistrarClienteUC registrarClienteUC;
    private final AutenticarClienteUC autenticarClienteUC;

    @PostMapping("/registrar")
    public ResponseEntity<RegistrarClienteResponse> registrar(@Valid @RequestBody RegistrarClienteRequest request) {
        RegistrarClienteResponse response = registrarClienteUC.run(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/validar")
    public ResponseEntity<AutenticarClienteResponse> validar(@Valid @RequestBody AutenticarClienteRequest request) {
        AutenticarClienteResponse response = autenticarClienteUC.run(request);
        return ResponseEntity.ok(response);
    }
}
