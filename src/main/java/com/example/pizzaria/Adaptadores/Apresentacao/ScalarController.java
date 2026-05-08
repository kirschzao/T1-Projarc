
package com.example.pizzaria.Adaptadores.Apresentacao;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = " Scalar", description = "Endpoint para o /docs")

public class ScalarController {

  @Operation(
        summary = "Carregar documentação do Scalar", 
        description = "Retorna a docs do Scalar"
    )
    @GetMapping(value = "/docs", produces = MediaType.TEXT_HTML_VALUE)
    public String docs() {
        return """
                <!doctype html>
                <html>
                  <head>
                    <title>API Reference</title>
                    <meta charset="utf-8" />
                    <meta name="viewport" content="width=device-width, initial-scale=1" />
                  </head>
                  <body>
                    <script
                      id="api-reference"
                      data-url="/v3/api-docs"
                      data-configuration='{"theme":"default","darkMode":true,"layout":"modern"}'>
                    </script>
                    <script src="https://cdn.jsdelivr.net/npm/@scalar/api-reference"></script>
                  </body>
                </html>
                """;
    }
}
