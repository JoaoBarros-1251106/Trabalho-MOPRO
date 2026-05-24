package org.example.exceptions;

/**
 * Exceção lançada quando a classificação é inválida.
 * Ex: valor fora do intervalo 1-10, já classificou, ou não viu o recurso.
 */
public class ClassificacaoInvalidaException extends Exception {

    public ClassificacaoInvalidaException(String mensagem) {
        super(mensagem);
    }
}