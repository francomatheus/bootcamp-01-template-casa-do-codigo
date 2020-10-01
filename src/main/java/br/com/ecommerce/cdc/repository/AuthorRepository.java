package br.com.ecommerce.cdc.repository;

import br.com.ecommerce.cdc.domain.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author,Long> {

    Optional<Author> findByEmail(String email);

}
