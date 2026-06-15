package com.example.pizzaria.Dominio.Servicos.Impostos;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ImpostoFactory {

    private final Map<String, IImpostoStrategy> estrategias;
    private final String leiAtiva;

    public ImpostoFactory(List<IImpostoStrategy> strategies,
                          @Value("${imposto.lei:Lei12345}") String leiAtiva) {
        this.estrategias = strategies.stream()
                .collect(Collectors.toMap(IImpostoStrategy::getCodigo, s -> s));
        this.leiAtiva = leiAtiva;

        if (!this.estrategias.containsKey(leiAtiva)) {
            throw new IllegalArgumentException(
                    "Lei de imposto '" + leiAtiva + "' nao encontrada. Disponiveis: " + this.estrategias.keySet());
        }
    }

    public IImpostoStrategy getAtivo() {
        return estrategias.get(leiAtiva);
    }

    public List<String> listarDisponiveis() {
        return List.copyOf(estrategias.keySet());
    }

    public String getLeiAtiva() {
        return leiAtiva;
    }
}
