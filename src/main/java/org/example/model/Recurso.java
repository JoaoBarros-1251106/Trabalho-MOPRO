package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Recurso implements Pesquisavel, Serializable {

    private String titulo;
    private int ano;
    private ArrayList<Genero> generos;
    private ArrayList<Ator> atores;

    public Recurso(String titulo, int ano) {
        this.titulo = titulo;
        this.ano = ano;
        this.generos = new ArrayList<>();
        this.atores = new ArrayList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAno() {
        return ano;
    }

    public ArrayList<Genero> getGeneros() {
        return generos;
    }

    public void adicionarGenero(Genero genero) {
        if (!generos.contains(genero)) {
            generos.add(genero);
        }
    }

    public ArrayList<Ator> getAtores() {
        return atores;
    }

    public void adicionarAtor(Ator ator) {
        if (!atores.contains(ator)) {
            atores.add(ator);
        }
    }

    public boolean isDuplicado(String titulo, int ano) {
        return this.titulo.equalsIgnoreCase(titulo) && this.ano == ano;
    }

    public abstract double getClassificacaoMedia();

    public abstract String getCategoriaClassificacao();

    @Override
    public boolean correspondePesquisa(String texto) {
        return titulo.toLowerCase().contains(texto.toLowerCase());
    }

    @Override
    public String toString() {
        return titulo + " (" + ano + ") | Géneros: " + generos;
    }
}
