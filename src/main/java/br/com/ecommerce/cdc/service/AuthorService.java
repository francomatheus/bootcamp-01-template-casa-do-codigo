package br.com.ecommerce.cdc.service;

import br.com.ecommerce.cdc.domain.form.AuthorForm;
import br.com.ecommerce.cdc.domain.response.AuthorResponse;

/**
 * Complexidade máxima permitida da classe - 7
 * Complexidade da classe - 2
 */


public interface AuthorService {
    // +2
    AuthorResponse createAuthor(AuthorForm authorForm);

}
