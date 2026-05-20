package org.example.model;

import java.util.ArrayList;

public class Temporada {

    private int numero;
    private ArrayList<Episodio> episodios;

    public Temporada(int numero) {
        this.numero = numero;
        this.episodios = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public void adicionarEpisodio(Episodio episodio) {
        if (!episodios.contains(episodio)) {
            episodios.add(episodio);
        }
    }

    public void removerEpisodio(Episodio episodio) {
        episodios.remove(episodio);
    }

    public ArrayList<Episodio> getEpisodios() {
        return episodios;
    }

    @Override
    public String toString() {
        return "Temporada " + numero + " | Episódios: " + episodios.size();
    }
}

//numero - indica o número da temporada (1, 2, 3...)
//ArrayList<Episodio> - cada temporada contém a sua própria lista de episódios
//contains - tal como fizeste na ListaPessoal, usamos para evitar adicionar o mesmo episódio duas vezes