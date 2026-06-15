package com.example.pizzaria.Adaptadores.Apresentacao;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pizzaria.Dominio.Servicos.Descontos.DescontoManager;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/descontos")
@CrossOrigin("*")
@Tag(name = "Descontos", description = "Gerenciamento das politicas de desconto")
public class DescontoController {

    private final DescontoManager descontoManager;

    @GetMapping("/lista")
    @Operation(summary = "Listar politicas de desconto disponiveis (UC3)")
    public List<Map<String, String>> listarPoliticas() {
        return descontoManager.listarDisponiveis();
    }

    @GetMapping("/corrente")
    @Operation(summary = "Consultar politica de desconto corrente")
    public ResponseEntity<Map<String, String>> consultarCorrente() {
        var ativo = descontoManager.getAtivo();
        return ResponseEntity.ok(Map.of(
                "codigo", ativo.getCodigo(),
                "descricao", ativo.getDescricao()));
    }

    @PutMapping("/corrente/{codigo}")
    @Operation(summary = "Definir politica de desconto corrente (UC4)")
    public ResponseEntity<Map<String, Object>> definirPolitica(@PathVariable String codigo) {
        descontoManager.setDescontoAtivo(codigo);
        return ResponseEntity.ok(Map.of(
                "mensagem", "Politica de desconto atualizada com sucesso.",
                "descontoCorrente", codigo));
    }
}
