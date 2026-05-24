package org.example.model;

/**
 * Representa um utilizador registado comum da plataforma.
 * Pode classificar, comentar, marcar como visto e gerir a sua lista pessoal.
 * Herda de UtilizadorRegistado.
 */

public class Espectador extends UtilizadorRegistado {

    /** Lista pessoal de filmes e episódios do espectador. */
    private ListaPessoal listaPessoal;

    /**
     * Constrói um espectador.
     * @param email    email do espectador
     * @param nome     username do espectador
     * @param password password do espectador
     */

    public Espectador(String email, String nome, String password) {
        super(email, nome, password);
        this.listaPessoal = new ListaPessoal();
    }

    /**
     * Devolve a lista pessoal do espectador.
     * @return lista pessoal
     */

    public ListaPessoal getListaPessoal() {
        return listaPessoal;
    }
}

//tem atributo proprio listaPessoal
//no construtor criamos uma ListaPessoal vazia