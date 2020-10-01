package br.com.ecommerce.cdc.service;

import br.com.ecommerce.cdc.domain.form.CategoriaForm;
import br.com.ecommerce.cdc.domain.model.Categoria;
import br.com.ecommerce.cdc.domain.response.CategoriaResponse;
import br.com.ecommerce.cdc.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

/**
 * Carga intrínseca máxima permitida - 7
 * Carga intrínseca da classe - 6
 */

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    // +1
    private CategoriaRepository categoriaRepository;

    @Override
    // +2 ( CategoriaResponse + CategoriaForm )
    public CategoriaResponse criaCategoria(CategoriaForm categoriaForm) {
        nomeCategoriaValido(categoriaForm);
        validacaoNomeCategoria(categoriaForm);
        // +1
        Categoria categoria = converterCategoriaFormToCategoria(categoriaForm);
        Categoria categoriaSalva = salvarNovaCategoria(categoria);
        CategoriaResponse categoriaResponse = converterCategoriaToCategoriaResponse(categoriaSalva);
        return categoriaResponse;
    }

    private void nomeCategoriaValido(CategoriaForm categoriaForm) {
        // +1
        if (categoriaForm.getNome() == null || categoriaForm.getNome()==""){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Nome não pode ser vazio!!");
        }
    }

    private Categoria converterCategoriaFormToCategoria(CategoriaForm categoriaForm) {
        Categoria categoria = new Categoria();
        categoria.setNome(categoriaForm.getNome());
        return categoria;
    }

    private Categoria salvarNovaCategoria(Categoria categoria) {
        Categoria categoriaSalva = categoriaRepository.save(categoria);
        return categoriaSalva;
    }

    private CategoriaResponse converterCategoriaToCategoriaResponse(Categoria categoriaSalva) {
        CategoriaResponse categoriaResponse = new CategoriaResponse();
        categoriaResponse.setId(categoriaSalva.getId());
        categoriaResponse.setNome(categoriaSalva.getNome());
        return categoriaResponse;
    }

    @Override
    public void validacaoNomeCategoria(CategoriaForm categoriaForm) {
        Optional<Categoria> categoriaByNome = categoriaRepository.findByNome(categoriaForm.getNome());
        // +1
        if (categoriaByNome.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Nome categoria já existe, escolha outro!!");
        }
    }
}
