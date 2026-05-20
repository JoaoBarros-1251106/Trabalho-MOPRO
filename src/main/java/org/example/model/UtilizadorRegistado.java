package org.example.model;

import java.io.Serializable;

public abstract class UtilizadorRegistado implements Serializable {
    private String email;
    private String nome;
    private String password;

    public UtilizadorRegistado(String email, String nome, String password) {
        this.email = email;
        this.nome = nome;
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public boolean temNome(String nome) {
        return this.nome.equals(nome);
    }

    public boolean temPassword(String password) {
        return this.password.equals(password);
    }

    @Override
    public String toString() {
        return nome + " <" + email + ">";
    }
}

//classe abstrata que representa um utilizador registado
//implements Serializable - necessário para gravar em ficheiro (serialização)
//Superclass de Admin e Espectador
//Constroi um utilizador registado
//verifica username e password correto
//getPassword() não é adicionado - má pratica de segurança expor a password
//boolean verificaEmail não usado porque não vamos usar o email para autenticação