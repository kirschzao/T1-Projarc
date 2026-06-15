package com.example.pizzaria.Adaptadores.Apresentacao;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pizzaria.Aplicacao.ConsultarDescontoCorrenteUC;
import com.example.pizzaria.Aplicacao.DefinirPoliticaDescontoUC;
import com.example.pizzaria.Aplicacao.ListarPoliticasDescontoUC;
import com.example.pizzaria.Aplicacao.Responses.DefinirPoliticaDescontoResponse;
import com.example.pizzaria.Aplicacao.Responses.PoliticaDescontoResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/descontos")
@Tag(name = "Descontos", description = "Gerenciamento das politicas de desconto")
public class DescontoController {

    private final ListarPoliticasDescontoUC listarPoliticasDescontoUC;
    private final ConsultarDescontoCorrenteUC consultarDescontoCorrenteUC;
    private final DefinirPoliticaDescontoUC definirPoliticaDescontoUC;

    @GetMapping("/lista")
    @CrossOrigin("*")
    @Operation(summary = "Listar politicas de desconto disponiveis (UC3)")
    public List<PoliticaDescontoResponse> listarPoliticas() {
        return listarPoliticasDescontoUC.run();
    }

    @GetMapping("/corrente")
    @CrossOrigin("*")
    @Operation(summary = "Consultar politica de desconto corrente")
    public PoliticaDescontoResponse consultarCorrente() {
        return consultarDescontoCorrenteUC.run();
    }

    @PutMapping("/corrente/{codigo}")
    @CrossOrigin("*")
    @Operation(summary = "Definir politica de desconto corrente (UC4)")
    public DefinirPoliticaDescontoResponse definirPolitica(@PathVariable String codigo) {
        return definirPoliticaDescontoUC.run(codigo);
    }
}
