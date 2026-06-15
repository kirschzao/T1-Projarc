package com.example.gateway.dto;

public record RegistrarRequest(String cpf, String nome, String celular, String endereco, String email, String senha) {}
