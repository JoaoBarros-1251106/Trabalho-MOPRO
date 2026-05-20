package org.example.model;

import org.example.utils.Data;

import java.io.Serializable;

public class Ator implements Pesquisavel, Serializable {
    private String nome;
    private Data dataNascimento;

    public Ator(String nome, Data dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {

        return nome;
    }
    public Data getDataNascimento() {
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
    public boolean correspondePesquisa(String texto) {
        return nome.toLowerCase().contains(texto.toLowerCase());
    }


}
