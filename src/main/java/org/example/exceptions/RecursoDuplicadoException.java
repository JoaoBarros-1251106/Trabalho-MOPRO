package org.example.exceptions;

/**
 * Exceção lançada quando se tenta adicionar um recurso
 * com o mesmo título e ano que outro já existente na plataforma.
 */
public class RecursoDuplicadoException extends Exception {

    /**
     * Constrói a exceção com uma mensagem de erro.
     * @param mensagem mensagem descritiva do erro
     */
    public RecursoDuplicadoException(String mensagem) {
        super(mensagem);
    }
}
