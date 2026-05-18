package org.example.model;

import org.example.utils.Data;

public class Ator {
    private String nome;
    private Data dataNascimento;

    public Ator(String nome, Data dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {

        return nome;
    }
    public String getDataNascimento() {

        return dataNascimento;
    }

    public boolean temNome(String nome) {
        return this.nome.equalsIgnoreCase(nome);
    }

    @Override
    public String toString() {
        return nome + " [" + dataNascimento + "]";
    }

    @Override
    public boolean correspondePesquisa(String texto){
        return nome.toUpperCase().contains(texto.toLowerCase());
    }


}
