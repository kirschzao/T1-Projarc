package com.example.pizzaria.Dominio.Servicos;

import org.springframework.stereotype.Service;

import com.example.pizzaria.Dominio.Dados.PedidoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DescontoService implements IDescontoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteService clienteService;

    public double calcularDesconto(String emailCliente, double valorTotal) {
        String cpf = clienteService.recuperarPorEmail(emailCliente).getCpf();
        int pedidosRecentes = pedidoRepository.contarPedidosPorClienteNoPeriodo(cpf, 20);

        if (pedidosRecentes > 3) {
            return valorTotal * 0.07;
        }
        return 0.0;
    }
}
