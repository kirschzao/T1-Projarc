package com.example.pizzaria.Adaptadores.Clientes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.pizzaria.Dominio.Entidades.ItemPedido;
import com.example.pizzaria.Dominio.Servicos.IEstoqueService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstoqueServiceClient implements IEstoqueService {

    private final EstoqueFeignClient estoqueFeignClient;

    @Override
    public boolean verificaDisponibilidade(List<ItemPedido> itens) {
        List<EstoqueFeignClient.ItemVerificacaoDTO> dtos = mapToDTO(itens);
        EstoqueFeignClient.VerificacaoResponse response = estoqueFeignClient.verificarDisponibilidade(dtos);
        return response.disponivel();
    }

    @Override
    public List<Long> identificarProdutosIndisponiveis(List<ItemPedido> itensDoPedido) {
        List<EstoqueFeignClient.ItemVerificacaoDTO> dtos = mapToDTO(itensDoPedido);
        EstoqueFeignClient.VerificacaoResponse response = estoqueFeignClient.verificarDisponibilidade(dtos);
        return response.produtosIndisponiveis();
    }

    @Override
    public void darBaixa(List<ItemPedido> itens) {
        List<EstoqueFeignClient.ItemVerificacaoDTO> dtos = mapToDTO(itens);
        estoqueFeignClient.darBaixa(dtos);
    }

    private List<EstoqueFeignClient.ItemVerificacaoDTO> mapToDTO(List<ItemPedido> itens) {
        return itens.stream()
                .map(item -> new EstoqueFeignClient.ItemVerificacaoDTO(
                        item.getItem().getId(),
                        item.getItem().getReceita().getId(),
                        item.getQuantidade()))
                .toList();
    }
}
