package com.example.pizzaria.Dominio.Servicos.Descontos;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class DescontoManager {

    private final Map<String, IDescontoStrategy> estrategias;
    private String codigoAtivo;

    public DescontoManager(List<IDescontoStrategy> strategies) {
        this.estrategias = strategies.stream()
                .collect(Collectors.toMap(IDescontoStrategy::getCodigo, s -> s));
        this.codigoAtivo = "Fidelidade";
    }

    public IDescontoStrategy getAtivo() {
        return estrategias.get(codigoAtivo);
    }

    public void setDescontoAtivo(String codigo) {
        if (!estrategias.containsKey(codigo)) {
            throw new IllegalArgumentException(
                    "Politica de desconto '" + codigo + "' nao encontrada. Disponiveis: " + estrategias.keySet());
        }
        this.codigoAtivo = codigo;
    }

    public String getCodigoAtivo() {
        return codigoAtivo;
    }

    public List<Map<String, String>> listarDisponiveis() {
        return estrategias.values().stream()
                .map(s -> Map.of("codigo", s.getCodigo(), "descricao", s.getDescricao()))
                .toList();
    }
}
