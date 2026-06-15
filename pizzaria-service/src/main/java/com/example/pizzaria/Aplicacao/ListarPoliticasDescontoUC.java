package com.example.pizzaria.Aplicacao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.pizzaria.Aplicacao.Responses.PoliticaDescontoResponse;
import com.example.pizzaria.Dominio.Servicos.Descontos.DescontoManager;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListarPoliticasDescontoUC {

    private final DescontoManager descontoManager;

    public List<PoliticaDescontoResponse> run() {
        return descontoManager.listarDisponiveis().stream()
                .map(s -> new PoliticaDescontoResponse(s.getCodigo(), s.getDescricao()))
                .toList();
    }
}
