package com.example.pizzaria.Aplicacao;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.pizzaria.Aplicacao.Requests.AutenticarClienteRequest;
import com.example.pizzaria.Aplicacao.Responses.AutenticarClienteResponse;
import com.example.pizzaria.Dominio.Entidades.Cliente;
import com.example.pizzaria.Dominio.Exceptions.RegraDeNegocioException;
import com.example.pizzaria.Dominio.Servicos.ClienteService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AutenticarClienteUC {
    private final ClienteService clienteService;
    private final PasswordEncoder passwordEncoder;

    public AutenticarClienteResponse run(AutenticarClienteRequest request) {
        Cliente cliente = clienteService.recuperarPorEmail(request.email());

        if (!passwordEncoder.matches(request.senha(), cliente.getSenha())) {
            throw new RegraDeNegocioException("Email ou senha inválidos.");
        }

        return new AutenticarClienteResponse(
            true,
            "Autenticação realizada com sucesso.",
            cliente.getNome(),
            cliente.getEmail()
        );
    }
}
