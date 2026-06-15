package com.example.pizzaria.Dominio.Servicos.Descontos;

import org.springframework.stereotype.Service;

import com.example.pizzaria.Dominio.Dados.PedidoRepository;
import com.example.pizzaria.Dominio.Servicos.ClienteService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DescontoFidelidade implements IDescontoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteService clienteService;

    @Override
    public String getCodigo() {
        return "Fidelidade";
    }

    @Override
    public String getDescricao() {
        return "7% de desconto para clientes com mais de 3 pedidos nos ultimos 20 dias";
    }

    @Override
    public double calcularDesconto(String emailCliente, double valorTotal) {
        String cpf = clienteService.recuperarPorEmail(emailCliente).getCpf();
        int pedidosRecentes = pedidoRepository.contarPedidosPorClienteNoPeriodo(cpf, 20);
        if (pedidosRecentes > 3) {
            return valorTotal * 0.07;
        }
        return 0.0;
    }
}
