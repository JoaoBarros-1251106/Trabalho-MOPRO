package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ListaPessoal implements Serializable {

    private ArrayList<Filme> filmes;
    private ArrayList<Episodio> episodios;

    public ListaPessoal() {
        this.filmes = new ArrayList<>();
        this.episodios = new ArrayList<>();
    }

    public void adicionarFilme(Filme filme) {
        if (!filmes.contains(filme)) {
            filmes.add(filme);
        }
    }

    public void removerFilme(Filme filme) {
        filmes.remove(filme);
    }

    public void adicionarEpisodio(Episodio episodio) {
        if (!episodios.contains(episodio)) {
            episodios.add(episodio);
        }
    }

    public void removerEpisodio(Episodio episodio) {
        episodios.remove(episodio);
    }

    public ArrayList<Filme> getFilmes() {
        return filmes;
    }

    public ArrayList<Episodio> getEpisodios() {
        return episodios;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ListaPessoal \n");
        sb.append("Filmes: (").append(filmes.size()).append("): \n");
        for (Filme filme : filmes) {
            sb.append(" - ").append(filme.getTitulo()).append("\n");
        }
        sb.append("Episodios: (").append(episodios.size()).append("): \n");
        for (Episodio episodio : episodios) {
            sb.append(" - ").append(episodio.getTitulo()).apend("\n");
        }
        return sb.toString();
    }
}

//lista de filmes e episodios
//lista pessoal vazia
//contains - verifica se já existe para não duplicar
