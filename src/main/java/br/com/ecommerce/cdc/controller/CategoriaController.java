package br.com.ecommerce.cdc.controller;

import br.com.ecommerce.cdc.domain.form.CategoriaForm;
import br.com.ecommerce.cdc.domain.response.CategoriaResponse;
import br.com.ecommerce.cdc.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Carga intrínseca máxima permitida - 7
 * Carga intrínseca da classe - 3
 */

@RestController
@RequestMapping("/v1/categoria")
public class CategoriaController {

    @Autowired
    // +1
    private CategoriaService categoriaService;

    @PostMapping
    // +2
    public ResponseEntity<CategoriaResponse> criarCategoria(@RequestBody CategoriaForm categoriaForm){
        CategoriaResponse categoriaResponse = categoriaService.criaCategoria(categoriaForm);

        return ResponseEntity.ok(categoriaResponse);
    }
}
