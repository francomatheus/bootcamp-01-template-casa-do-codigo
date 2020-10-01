package br.com.ecommerce.cdc.domain.form;

public class CategoriaForm {

    private String nome;

    public CategoriaForm() {
    }

    public CategoriaForm(String nome){
        this.nome=nome;
    }

    public String getNome() {
        return nome;
    }

}
