package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Representa a lista pessoal de um espectador.
 * Contém filmes e episódios guardados pelo espectador.
 * Implementa Serializable para persistência em ficheiro.
 */

public class ListaPessoal implements Serializable {

    /** Lista de filmes guardados. */
    private ArrayList<Filme> filmes;

    /** Lista de episódios guardados. */
    private ArrayList<Episodio> episodios;

    /**
     * Constrói uma lista pessoal vazia.
     */

    public ListaPessoal() {
        this.filmes = new ArrayList<>();
        this.episodios = new ArrayList<>();
    }

    /**
     * Adiciona um filme à lista pessoal (se ainda não existir).
     * @param filme filme a adicionar
     */

    public void adicionarFilme(Filme filme) {
        if (!filmes.contains(filme)) {
            filmes.add(filme);
        }
    }

    /**
     * Remove um filme da lista pessoal.
     * @param filme filme a remover
     */

    public void removerFilme(Filme filme) {
        filmes.remove(filme);
    }

    /**
     * Adiciona um episódio à lista pessoal (se ainda não existir).
     * @param episodio episódio a adicionar
     */

    public void adicionarEpisodio(Episodio episodio) {
        if (!episodios.contains(episodio)) {
            episodios.add(episodio);
        }
    }

    /**
     * Remove um episódio da lista pessoal.
     * @param episodio episódio a remover
     */

    public void removerEpisodio(Episodio episodio) {
        episodios.remove(episodio);
    }

    /**
     * Devolve a lista de filmes guardados.
     * @return lista de filmes
     */

    public ArrayList<Filme> getFilmes() {
        return filmes;
    }

    /**
     * Devolve a lista de episódios guardados.
     * @return lista de episódios
     */

    public ArrayList<Episodio> getEpisodios() {
        return episodios;
    }

    /**
     * Devolve uma representação textual da lista pessoal.
     * @return filmes e episódios guardados
     */

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