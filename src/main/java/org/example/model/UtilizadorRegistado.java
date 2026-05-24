package org.example.model;

import java.io.Serializable;

/**
 * Classe abstrata que representa um utilizador registado na plataforma.
 * Superclasse de Admin e Espectador.
 * Implementa Serializable para persistência em ficheiro.
 */

public abstract class UtilizadorRegistado implements Serializable {
    /** Email do utilizador. */
    private String email;
    /** Nome/username do utilizador. */
    private String nome;
    /** Password do utilizador. */
    private String password;

    /**
     * Constrói um utilizador registado.
     * @param email    email do utilizador
     * @param nome     username do utilizador
     * @param password password do utilizador
     */

    public UtilizadorRegistado(String email, String nome, String password) {
        this.email = email;
        this.nome = nome;
        this.password = password;
    }

    /**
     * Devolve o nome/username do utilizador.
     * @return nome
     */

    public String getNome() {
        return nome;
    }

    /**
     * Devolve o email do utilizador.
     * @return email
     */

    public String getEmail() {
        return email;
    }

    /**
     * Verifica se o utilizador tem o username indicado.
     * @param nome username a comparar
     * @return true se coincidir
     */

    public boolean temNome(String nome) {
        return this.nome.equals(nome);
    }


    /**
     * Verifica se a password está correta.
     * @param password password a verificar
     * @return true se coincidir
     */

    public boolean temPassword(String password) {
        return this.password.equals(password);
    }

    /**
     * Devolve uma representação textual do utilizador.
     * @return nome e email
     */

    @Override
    public String toString() {
        return nome + " <" + email + ">";
    }
}

