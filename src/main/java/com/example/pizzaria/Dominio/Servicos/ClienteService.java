package com.example.pizzaria.Dominio.Servicos;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.pizzaria.Dominio.Dados.ClienteRepository;
import com.example.pizzaria.Dominio.Entidades.Cliente;
import com.example.pizzaria.Dominio.Exceptions.RecursoNaoEncontradoException;
import com.example.pizzaria.Dominio.Exceptions.RegraDeNegocioException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;

    public Cliente recuperarPorCpf(String cpf) {
        Cliente cliente = clienteRepository.recuperarPorCpf(cpf);
        if (cliente == null) {
            throw new RecursoNaoEncontradoException("Cliente com CPF " + cpf + " não encontrado.");
        }
        return cliente;
    }

    public Cliente recuperarPorEmail(String email) {
        Cliente cliente = clienteRepository.recuperarPorEmail(email);
        if (cliente == null) {
            throw new RecursoNaoEncontradoException("Cliente com email " + email + " não encontrado.");
        }
        return cliente;
    }

    public void salvar(Cliente cliente) {
        clienteRepository.salvar(cliente);
    }

    public boolean emailJaCadastrado(String email) {
        return clienteRepository.recuperarPorEmail(email) != null;
    }

    public Cliente registrar(String cpf, String nome, String celular, String endereco, String email, String senha) {
        if (emailJaCadastrado(email)) {
            throw new RegraDeNegocioException("Já existe um cliente registrado com este email.");
        }
        String senhaCriptografada = passwordEncoder.encode(senha);
        Cliente novoCliente = new Cliente(cpf, nome, celular, endereco, email, senhaCriptografada);
        clienteRepository.salvar(novoCliente);
        return novoCliente;
    }
}
