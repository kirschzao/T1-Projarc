package com.example.pizzaria.Dominio.Servicos;

import com.example.pizzaria.Dominio.Entidades.Cliente;

public interface IClienteService {
    Cliente recuperarPorCpf(String cpf);
    Cliente recuperarPorEmail(String email);
    void salvar(Cliente cliente);
}
