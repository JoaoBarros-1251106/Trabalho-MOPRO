package org.example.exceptions;

/**
 * Exceção lançada quando a classificação é inválida.
 * Por exemplo: valor fora do intervalo 1-10, já classificou, ou não viu o recurso.
 */
public class ClassificacaoInvalidaException extends Exception {

    /**
     * Constrói a exceção com uma mensagem de erro.
     * @param mensagem mensagem descritiva do erro
     */
    public ClassificacaoInvalidaException(String mensagem) {

        super(mensagem);
    }
}