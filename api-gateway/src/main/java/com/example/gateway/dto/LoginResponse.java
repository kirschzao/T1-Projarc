package com.example.gateway.dto;

public record LoginResponse(boolean sucesso, String mensagem, String nome, String email, String role, String token) {}
