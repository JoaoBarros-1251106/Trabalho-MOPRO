package org.example.model;

public class Espectador extends UtilizadorRegistado {

    private ListaPessoal listaPessoal;

    public Espectador(String email, String nome, String password) {
        super(email, nome, password);
        this.listaPessoal = new ListaPessoal();
    }

    public ListaPessoal getListaPessoal() {
        return listaPessoal;
    }
}

//tem atributo proprio listaPessoal
//no construtor criamos uma ListaPessoal vazia