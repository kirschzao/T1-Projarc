package com.example.pizzaria.Dominio.Servicos.Descontos;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DescontoManager {

    private final Map<String, IDescontoService> estrategias;
    private final JdbcTemplate jdbcTemplate;

    public DescontoManager(List<IDescontoService> strategies, JdbcTemplate jdbcTemplate) {
        this.estrategias = strategies.stream()
                .collect(Collectors.toMap(IDescontoService::getCodigo, s -> s));
        this.jdbcTemplate = jdbcTemplate;
    }

    public IDescontoService getAtivo() {
        return estrategias.get(getCodigoAtivo());
    }

    public void setDescontoAtivo(String codigo) {
        if (!estrategias.containsKey(codigo)) {
            throw new IllegalArgumentException(
                    "Politica de desconto '" + codigo + "' nao encontrada. Disponiveis: " + estrategias.keySet());
        }
        jdbcTemplate.update("UPDATE configuracoes SET valor = ? WHERE chave = 'desconto_ativo'", codigo);
    }

    public String getCodigoAtivo() {
        return jdbcTemplate.queryForObject(
            "SELECT valor FROM configuracoes WHERE chave = 'desconto_ativo'", String.class);
    }

    public List<IDescontoService> listarDisponiveis() {
        return List.copyOf(estrategias.values());
    }
}
