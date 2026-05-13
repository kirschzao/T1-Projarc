package com.example.pizzaria.Dominio.Servicos;

import org.springframework.stereotype.Service;

import com.example.pizzaria.Dominio.Dados.ClienteRepository;
import com.example.pizzaria.Dominio.Entidades.Cliente;

@Service
public class ClienteService implements IClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente recuperarPorCpf(String cpf) {
        return clienteRepository.recuperarPorCpf(cpf);
    }

    @Override
    public Cliente recuperarPorEmail(String email) {
        return clienteRepository.recuperarPorEmail(email);
    }

    @Override
    public void salvar(Cliente cliente) {
        clienteRepository.salvar(cliente);
    }
}
