package br.com.ecommerce.cdc.service;

import br.com.ecommerce.cdc.domain.AuthorForm;
import br.com.ecommerce.cdc.domain.AuthorResponse;

/**
 * Complexidade máxima permitida da classe - 7
 * Complexidade da classe - 2
 */


public interface AuthorService {
    // +2
    AuthorResponse createAuthor(AuthorForm authorForm);

}
