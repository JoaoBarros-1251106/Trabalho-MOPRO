package org.example.exceptions;

/**
 * Exceção lançada quando um ator pesquisado não é encontrado na plataforma.
 */
public class AtorNaoEncontradoException extends Exception {

    /**
     * Constrói a exceção com uma mensagem de erro.
     * @param mensagem mensagem descritiva do erro
     */
    public AtorNaoEncontradoException(String mensagem) {

        super(mensagem);
    }
}