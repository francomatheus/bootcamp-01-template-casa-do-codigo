package br.com.ecommerce.cdc.service;

import br.com.ecommerce.cdc.domain.form.CategoriaForm;
import br.com.ecommerce.cdc.domain.response.CategoriaResponse;

/**
 * Carga intrínseca máxima permitida - 7
 * Carga intrínseca da classe - 2
 */

public interface CategoriaService {

    // +2
    CategoriaResponse criaCategoria(CategoriaForm categoriaForm);

    void validacaoNomeCategoria(CategoriaForm categoriaForm);
}
