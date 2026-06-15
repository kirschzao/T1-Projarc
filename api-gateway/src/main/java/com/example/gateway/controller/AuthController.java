package com.example.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.example.gateway.config.JwtUtil;
import com.example.gateway.dto.LoginRequest;
import com.example.gateway.dto.LoginResponse;
import com.example.gateway.dto.RegistrarRequest;
import com.example.gateway.dto.ValidarResponse;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final WebClient.Builder webClientBuilder;
    private final JwtUtil jwtUtil;

    public AuthController(WebClient.Builder webClientBuilder, JwtUtil jwtUtil) {
        this.webClientBuilder = webClientBuilder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public Mono<LoginResponse> login(@RequestBody LoginRequest request) {
        return webClientBuilder.build()
                .post()
                .uri("lb://pizzaria-service/internal/auth/validar")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                    response -> Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email ou senha inválidos.")))
                .bodyToMono(ValidarResponse.class)
                .map(validar -> {
                    String token = jwtUtil.generateToken(validar.email());
                    return new LoginResponse(true, "Login realizado com sucesso.", validar.nome(), validar.email(), token);
                });
    }

    @PostMapping("/registrar")
    public Mono<Object> registrar(@RequestBody RegistrarRequest request) {
        return webClientBuilder.build()
                .post()
                .uri("lb://pizzaria-service/internal/auth/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                    response -> response.bodyToMono(String.class)
                        .flatMap(body -> Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, body))))
                .bodyToMono(Object.class);
    }
}
