package com.example.pizzaria.Aplicacao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.pizzaria.Aplicacao.Responses.ListagemPedidoResponse;
import com.example.pizzaria.Dominio.Dados.ClienteRepository;
import com.example.pizzaria.Dominio.Dados.PedidoRepository;
import com.example.pizzaria.Dominio.Entidades.Cliente;
import com.example.pizzaria.Dominio.Exceptions.RecursoNaoEncontradoException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ListarPedidosClienteEntreguesUC {
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;

    public List<ListagemPedidoResponse> run(String emailAutenticado, LocalDateTime inicio, LocalDateTime fim) {
        Cliente cliente = clienteRepository.recuperarPorEmail(emailAutenticado);
        if (cliente == null) {
            throw new RecursoNaoEncontradoException("Cliente não encontrado.");
        }

        return pedidoRepository.recuperarEntreguesPorClienteEData(cliente.getCpf(), inicio, fim).stream()
            .map(p -> new ListagemPedidoResponse(
                p.getId(),
                p.getCliente().getCpf(),
                p.getStatus().name(),
                p.getValorCobrado(),
                p.getDataCriacao()
            )).collect(Collectors.toList());
    }
}
