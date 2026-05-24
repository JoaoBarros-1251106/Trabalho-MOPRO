package org.example.exceptions;

/**
 * Exceção lançada quando um ator não é encontrado na plataforma.
 */
public class AtorNaoEncontradoException extends Exception {

    public AtorNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}