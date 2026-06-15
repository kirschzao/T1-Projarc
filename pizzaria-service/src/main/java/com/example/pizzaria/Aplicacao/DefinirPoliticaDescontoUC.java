package com.example.pizzaria.Aplicacao;

import org.springframework.stereotype.Service;

import com.example.pizzaria.Aplicacao.Responses.DefinirPoliticaDescontoResponse;
import com.example.pizzaria.Dominio.Servicos.Descontos.DescontoManager;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DefinirPoliticaDescontoUC {

    private final DescontoManager descontoManager;

    public DefinirPoliticaDescontoResponse run(String codigo) {
        descontoManager.setDescontoAtivo(codigo);
        return new DefinirPoliticaDescontoResponse("Politica de desconto atualizada com sucesso.", codigo);
    }
}
