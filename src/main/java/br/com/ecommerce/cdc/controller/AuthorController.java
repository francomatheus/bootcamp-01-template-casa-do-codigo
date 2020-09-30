package br.com.ecommerce.cdc.controller;

import br.com.ecommerce.cdc.domain.AuthorForm;
import br.com.ecommerce.cdc.domain.AuthorResponse;
import br.com.ecommerce.cdc.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/autor")
public class AuthorController {

    /*
    *Complexidade cognitiva -> Máximo 7
    * Complexidade - 3
    */

    @Autowired
    // +1
    private AuthorService authorService;

    @PostMapping
    // + 2 ( AuthorResponse e AuthorForm)
    public ResponseEntity<AuthorResponse> createAuthor(@RequestBody AuthorForm authorForm) throws URISyntaxException {

        AuthorResponse author = authorService.createAuthor(authorForm);

        return ResponseEntity.created(new URI(String.format("/autor/%s", author.getId()))).body(author);
    }
}
