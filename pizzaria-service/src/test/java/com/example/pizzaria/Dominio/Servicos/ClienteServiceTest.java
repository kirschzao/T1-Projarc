package com.example.pizzaria.Dominio.Servicos;

import com.example.pizzaria.Dominio.Dados.ClienteRepository;
import com.example.pizzaria.Dominio.Entidades.Cliente;
import com.example.pizzaria.Dominio.Exceptions.RecursoNaoEncontradoException;
import com.example.pizzaria.Dominio.Exceptions.RegraDeNegocioException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock private ClienteRepository clienteRepository;
    @Mock private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente clienteTest() {
        return new Cliente("12345678900", "João", "51999999999",
                "Rua A", "joao@email.com", "hashSenha", "CLIENTE");
    }

    @Test
    void deveRecuperarClientePorEmail() {
        when(clienteRepository.recuperarPorEmail("joao@email.com")).thenReturn(clienteTest());

        Cliente cliente = clienteService.recuperarPorEmail("joao@email.com");

        assertEquals("João", cliente.getNome());
        assertEquals("joao@email.com", cliente.getEmail());
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoEncontradoPorEmail() {
        when(clienteRepository.recuperarPorEmail("naoexiste@email.com")).thenReturn(null);

        assertThrows(RecursoNaoEncontradoException.class,
                () -> clienteService.recuperarPorEmail("naoexiste@email.com"));
    }

    @Test
    void deveRecuperarClientePorCpf() {
        when(clienteRepository.recuperarPorCpf("12345678900")).thenReturn(clienteTest());

        Cliente cliente = clienteService.recuperarPorCpf("12345678900");

        assertNotNull(cliente);
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoEncontradoPorCpf() {
        when(clienteRepository.recuperarPorCpf("00000000000")).thenReturn(null);

        assertThrows(RecursoNaoEncontradoException.class,
                () -> clienteService.recuperarPorCpf("00000000000"));
    }

    @Test
    void deveRegistrarNovoCliente() {
        when(clienteRepository.recuperarPorEmail("novo@email.com")).thenReturn(null);
        when(passwordEncoder.encode("senha123")).thenReturn("hashSenha");

        Cliente registrado = clienteService.registrar(
                "99999999999", "Novo", "51888888888", "Rua B", "novo@email.com", "senha123");

        assertNotNull(registrado);
        assertEquals("novo@email.com", registrado.getEmail());
        verify(clienteRepository).salvar(any(Cliente.class));
    }

    @Test
    void deveLancarExcecaoAoRegistrarEmailDuplicado() {
        when(clienteRepository.recuperarPorEmail("joao@email.com")).thenReturn(clienteTest());

        assertThrows(RegraDeNegocioException.class,
                () -> clienteService.registrar(
                        "99999999999", "Dup", "51888888888", "Rua B", "joao@email.com", "senha123"));
    }

    @Test
    void deveVerificarEmailJaCadastrado() {
        when(clienteRepository.recuperarPorEmail("joao@email.com")).thenReturn(clienteTest());
        when(clienteRepository.recuperarPorEmail("novo@email.com")).thenReturn(null);

        assertTrue(clienteService.emailJaCadastrado("joao@email.com"));
        assertFalse(clienteService.emailJaCadastrado("novo@email.com"));
    }
}
