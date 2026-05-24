package org.example.model;

/**
 * Interface que define o contrato de pesquisa por texto.
 * Implementada por Ator, Filme e Serie.
 */

public interface Pesquisavel {
    /**
     * Verifica se o objeto corresponde ao texto de pesquisa.
     * @param texto texto a pesquisar
     * @return true se corresponder
     */

    boolean correspondePesquisa(String texto);
}

