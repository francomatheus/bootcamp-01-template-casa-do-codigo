package br.com.ecommerce.cdc.service;

import br.com.ecommerce.cdc.domain.Author;
import br.com.ecommerce.cdc.domain.AuthorForm;
import br.com.ecommerce.cdc.domain.AuthorResponse;
import br.com.ecommerce.cdc.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AuthorServiceImpl implements AuthorService {

    /*
    * Complexiade cognitiva máxima aceitavel - 7
    * Complexidade da classe - 12
    */

    @Autowired
    // +1
    private AuthorRepository authorRepository;

    @Override
    // +2
    public AuthorResponse createAuthor(AuthorForm authorForm) {
        AuthorForm authorFormValidate = validateAuthorForm(authorForm);
        //+1
        Author author = converterAuthorFormToModel(authorFormValidate);
        Author authorSaved = saveAuthor(author);
        return converterAuthorToResponse(authorSaved);
    }

    private AuthorForm validateAuthorForm(AuthorForm authorForm) {
        Optional<AuthorForm> authorFormOptional = Optional.of(authorForm);
        //+1
        if (authorFormOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Body can't be empty!!!");
        }
        // +1
        else {
            //+1
            if (authorForm.getName() == null || authorForm.getName() == ""){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name can't be empty!!!");
            }
            //+1
            else if (authorForm.getEmail() == null || authorForm.getEmail() == ""){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email can't be empty!!!");
            }
            //+1
            else if (authorForm.getDescription() == null || authorForm.getDescription() == ""){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Description can't be empty!!!");
            }
            validateEmail(authorForm);
            uniqueEmail(authorForm);
            AuthorForm authorFormValidate = validateLengthDescription(authorForm);

            return authorFormValidate;
        }
    }

    private void validateEmail(AuthorForm authorForm) {
        String email = authorForm.getEmail();
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        //+1
        if (!matcher.matches()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Email invalid!!!");
        }
    }

    private void uniqueEmail(AuthorForm authorForm) {
        Optional<Author> authorByEmail = authorRepository.findByEmail(authorForm.getEmail());
        //+1
        if (!authorByEmail.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Email already saved, choose other!!!");
        }
    }

    private AuthorForm validateLengthDescription(AuthorForm authorForm) {
        String description = authorForm.getDescription();
        //+1
        if(description.length() > 400){
            authorForm.setDescription(description.substring(0,400));
            return authorForm;
        }
        return authorForm;
    }

    private Author converterAuthorFormToModel(AuthorForm authorFormValidate) {
        Author author = new Author();
        author.setName(authorFormValidate.getName());
        author.setEmail(authorFormValidate.getEmail());
        author.setDescription(authorFormValidate.getDescription());
        author.setInstant(LocalDateTime.now(ZoneId.of("UTC")));
        return author;
    }

    @Transactional
    private Author saveAuthor(Author author) {
        Author authorSaved = authorRepository.save(author);
        return authorSaved;
    }

    private AuthorResponse converterAuthorToResponse(Author authorSaved) {
        AuthorResponse authorResponse = new AuthorResponse();
        authorResponse.setId(authorSaved.getId());
        authorResponse.setName(authorSaved.getName());
        authorResponse.setEmail(authorSaved.getEmail());
        authorResponse.setDescription(authorSaved.getDescription());
        authorResponse.setInstant(authorSaved.getInstant());
        return authorResponse;
    }
}
