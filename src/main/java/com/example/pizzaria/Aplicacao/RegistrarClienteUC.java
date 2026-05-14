package com.example.pizzaria.Aplicacao;

import org.springframework.stereotype.Component;

import com.example.pizzaria.Aplicacao.Requests.RegistrarClienteRequest;
import com.example.pizzaria.Aplicacao.Responses.RegistrarClienteResponse;
import com.example.pizzaria.Dominio.Servicos.ClienteService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RegistrarClienteUC {
    private final ClienteService clienteService;

    public RegistrarClienteResponse run(RegistrarClienteRequest request) {
        clienteService.registrar(request.cpf(), request.nome(), request.celular(),
            request.endereco(), request.email(), request.senha());
        return new RegistrarClienteResponse(true, "Cliente registrado com sucesso.", request.email());
    }
}
