package com.example.pizzaria.Dominio.Dados;

import com.example.pizzaria.Dominio.Entidades.Cliente;

public interface ClienteRepository {
    Cliente recuperarPorCpf(String cpf);
    Cliente recuperarPorEmail(String email);
    void salvar(Cliente cliente);
}
