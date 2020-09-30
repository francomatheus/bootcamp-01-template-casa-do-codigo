package br.com.ecommerce.cdc.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "author")
public class Author {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String description;
    private LocalDateTime instant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getInstant() {
        return instant;
    }

    public void setInstant(LocalDateTime instant) {
        this.instant = instant;
    }
}