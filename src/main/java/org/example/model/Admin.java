package org.example.model;

/**
 * Representa um administrador da plataforma.
 * Tem acesso total ao sistema: gerir recursos, atores e utilizadores.
 */
public class Admin extends UtilizadorRegistado {
    /**
     *
     * Constrói um administrador.
     *
     * @param email    do administrador
     * @param nome     do administrador
     * @param password do administrador
     */
    public Admin(String email, String nome, String password) {
        super(email, nome, password);
    }
}

//extends - herda de UtilizadorRegistado
//super - chama o construtor da classe mãe