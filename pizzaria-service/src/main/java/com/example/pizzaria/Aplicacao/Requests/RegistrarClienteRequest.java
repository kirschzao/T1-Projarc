package com.example.pizzaria.Aplicacao.Requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Requisição para registrar um novo cliente")
public record RegistrarClienteRequest(
    @Schema(description = "CPF do cliente (sem formatação)", example = "12345678900")
    @NotBlank(message = "CPF é obrigatório")
    String cpf,

    @Schema(description = "Nome completo do cliente", example = "João Silva")
    @NotBlank(message = "Nome é obrigatório")
    String nome,

    @Schema(description = "Email do cliente", example = "joao.silva@email.com")
    @Email(message = "Email deve ser válido")
    @NotBlank(message = "Email é obrigatório")
    String email,

    @Schema(description = "Celular do cliente", example = "51999999999")
    @NotBlank(message = "Celular é obrigatório")
    String celular,

    @Schema(description = "Endereço de entrega", example = "Rua das Flores, 200")
    @NotBlank(message = "Endereço é obrigatório")
    String endereco,

    @Schema(description = "Senha do cliente", example = "123456")
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    String senha
) {}
