package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ListaPessoal implements Serializable {

    // Lista de filmes guardados
    private ArrayList<Filme> filmes;

    // Lista de episódios guardados
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
        StringBuilder sb = new StringBuilder("Lista Pessoal\n");
        sb.append("Filmes (").append(filmes.size()).append("):\n");
        for (Filme f : filmes) {
            sb.append(" - ").append(f.getTitulo()).append("\n");
        }
        sb.append("Episódios (").append(episodios.size()).append("):\n");
        for (Episodio ep : episodios) {
            sb.append(" - ").append(ep.getTitulo()).append("\n");
        }
        return sb.toString();
    }
}