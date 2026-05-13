package com.example.pizzaria.Aplicacao;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.pizzaria.Aplicacao.Requests.RegistrarClienteRequest;
import com.example.pizzaria.Aplicacao.Responses.RegistrarClienteResponse;
import com.example.pizzaria.Dominio.Entidades.Cliente;
import com.example.pizzaria.Dominio.Servicos.IClienteService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RegistrarClienteUC {
    private final IClienteService clienteService;
    private final PasswordEncoder passwordEncoder;

    public RegistrarClienteResponse run(RegistrarClienteRequest request) {
        // Validar se cliente com este email já existe
        Cliente clienteExistente = clienteService.recuperarPorEmail(request.email());
        if (clienteExistente != null) {
            return new RegistrarClienteResponse(
                false,
                "Já existe um cliente registrado com este email.",
                request.email()
            );
        }

        // Criptografar a senha
        String senhaCriptografada = passwordEncoder.encode(request.senha());

        // Criar novo cliente
        Cliente novoCliente = new Cliente(
            request.cpf(),
            request.nome(),
            request.celular(),
            request.endereco(),
            request.email(),
            senhaCriptografada
        );

        // Salvar cliente
        try {
            clienteService.salvar(novoCliente);
            return new RegistrarClienteResponse(
                true,
                "Cliente registrado com sucesso.",
                request.email()
            );
        } catch (Exception e) {
            return new RegistrarClienteResponse(
                false,
                "Erro ao registrar cliente: " + e.getMessage(),
                request.email()
            );
        }
    }
}
