package org.example.exceptions;

/**
 * Exceção lançada quando se tenta adicionar um recurso
 * com o mesmo título e ano que outro já existente.
 */
public class RecursoDuplicadoException extends Exception {

    public RecursoDuplicadoException(String mensagem) {
        super(mensagem);
    }
}
