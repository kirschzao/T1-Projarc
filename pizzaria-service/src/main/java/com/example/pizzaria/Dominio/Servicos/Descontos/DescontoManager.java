package com.example.pizzaria.Dominio.Servicos.Descontos;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class DescontoManager {

    private final Map<String, IDescontoService> estrategias;
    private String codigoAtivo;

    public DescontoManager(List<IDescontoService> strategies) {
        this.estrategias = strategies.stream()
                .collect(Collectors.toMap(IDescontoService::getCodigo, s -> s));
        this.codigoAtivo = "Fidelidade";
    }

    public IDescontoService getAtivo() {
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

    public List<IDescontoService> listarDisponiveis() {
        return List.copyOf(estrategias.values());
    }
}
