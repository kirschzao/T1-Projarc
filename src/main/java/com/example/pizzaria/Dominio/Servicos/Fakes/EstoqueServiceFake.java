package com.example.pizzaria.Dominio.Servicos.Fakes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.pizzaria.Dominio.Entidades.ItemPedido;
import com.example.pizzaria.Dominio.Servicos.EstoqueService;

@Service
public class EstoqueServiceFake implements EstoqueService {

    @Override
    public boolean verificaDisponibilidade(List<ItemPedido> itens) {
        return true;
    }

    @Override
    public List<Long> identificarProdutosIndisponiveis(List<ItemPedido> itensDoPedido) {
        return List.of();
    }
}
