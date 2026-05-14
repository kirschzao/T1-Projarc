package com.example.pizzaria.Aplicacao;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
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
    private final AuthenticationManager authenticationManager;
    private final ClienteService clienteService;

    public AutenticarClienteResponse run(AutenticarClienteRequest request) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.senha())
            );
        } catch (AuthenticationException e) {
            throw new RegraDeNegocioException("Email ou senha inválidos.");
        }

        Cliente cliente = clienteService.recuperarPorEmail(request.email());
        return new AutenticarClienteResponse(true, "Autenticação realizada com sucesso.", cliente.getNome(), cliente.getEmail());
    }
}
