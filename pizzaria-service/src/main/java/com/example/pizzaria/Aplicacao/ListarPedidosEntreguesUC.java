package com.example.pizzaria.Aplicacao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.pizzaria.Aplicacao.Responses.ListagemPedidoResponse;
import com.example.pizzaria.Dominio.Dados.PedidoRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ListarPedidosEntreguesUC {
    private final PedidoRepository pedidoRepository;

    public List<ListagemPedidoResponse> run(LocalDateTime inicio, LocalDateTime fim) {
        return pedidoRepository.recuperarEntreguesPorData(inicio, fim).stream()
            .map(p -> new ListagemPedidoResponse(
                p.getId(),
                p.getCliente().getCpf(),
                p.getStatus().name(),
                p.getValorCobrado(),
                p.getDataCriacao()
            )).collect(Collectors.toList());
    }
}
