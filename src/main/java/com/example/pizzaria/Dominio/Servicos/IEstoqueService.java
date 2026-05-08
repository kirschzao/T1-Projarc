package com.example.pizzaria.Dominio.Servicos;

import org.springframework.stereotype.Service;
import com.example.pizzaria.Dominio.Entidades.ItemPedido;
import java.util.List;

public interface IEstoqueService {
    boolean verificaDisponibilidade(List<ItemPedido> itens);
}

@Service
class EstoqueServiceFake implements IEstoqueService {
    @Override
    public boolean verificaDisponibilidade(List<ItemPedido> itens) {
        return true; 
    }
}