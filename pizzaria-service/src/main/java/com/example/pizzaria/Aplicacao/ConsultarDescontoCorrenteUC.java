package com.example.pizzaria.Aplicacao;

import org.springframework.stereotype.Service;

import com.example.pizzaria.Aplicacao.Responses.PoliticaDescontoResponse;
import com.example.pizzaria.Dominio.Servicos.Descontos.DescontoManager;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConsultarDescontoCorrenteUC {

    private final DescontoManager descontoManager;

    public PoliticaDescontoResponse run() {
        var ativo = descontoManager.getAtivo();
        return new PoliticaDescontoResponse(ativo.getCodigo(), ativo.getDescricao());
    }
}
