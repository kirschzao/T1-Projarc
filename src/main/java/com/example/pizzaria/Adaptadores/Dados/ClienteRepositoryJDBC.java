package com.example.pizzaria.Adaptadores.Dados;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.pizzaria.Dominio.Dados.ClienteRepository;
import com.example.pizzaria.Dominio.Entidades.Cliente;

@Repository
public class ClienteRepositoryJDBC implements ClienteRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ClienteRepositoryJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Cliente recuperarPorCpf(String cpf) {
        String sql = "SELECT cpf, nome, celular, endereco, email FROM clientes WHERE cpf = ?";
        List<Cliente> clientes = jdbcTemplate.query(sql,
            ps -> ps.setString(1, cpf),
            (rs, rowNum) -> new Cliente(
                rs.getString("cpf"),
                rs.getString("nome"),
                rs.getString("celular"),
                rs.getString("endereco"),
                rs.getString("email")
            )
        );
        return clientes.isEmpty() ? null : clientes.getFirst();
    }

    @Override
    public Cliente recuperarPorEmail(String email) {
        String sql = "SELECT cpf, nome, celular, endereco, email FROM clientes WHERE email = ?";
        List<Cliente> clientes = jdbcTemplate.query(sql,
            ps -> ps.setString(1, email),
            (rs, rowNum) -> new Cliente(
                rs.getString("cpf"),
                rs.getString("nome"),
                rs.getString("celular"),
                rs.getString("endereco"),
                rs.getString("email")
            )
        );
        return clientes.isEmpty() ? null : clientes.getFirst();
    }

    @Override
    public void salvar(Cliente cliente) {
        String sql = "INSERT INTO clientes (cpf, nome, celular, endereco, email) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
            cliente.getCpf(),
            cliente.getNome(),
            cliente.getCelular(),
            cliente.getEndereco(),
            cliente.getEmail()
        );
    }
}
