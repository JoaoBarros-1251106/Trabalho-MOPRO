package org.example.model;

import org.example.utils.Data;
import java.util.ArrayList;

public class Ator implements Pesquisavel {
    private String nome;
    private Data dataNascimento;

    // Nova lista para guardar os filmes em que o ator participou
    private ArrayList<Filme> filmesParticipados;

    public Ator(String nome, Data dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.filmesParticipados = new ArrayList<>(); // Inicialização da lista
    }

    public String getNome() {
        return nome;
    }

    public Data getDataNascimento() {
        return dataNascimento;
    }

    // Método para associar um filme a este ator
    public void adicionarFilme(Filme filme) {
        if (!filmesParticipados.contains(filme)) {
            filmesParticipados.add(filme);
        }
    }

    public int getNumeroParticipacoes() {
        return filmesParticipados.size();
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