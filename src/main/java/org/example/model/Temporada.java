package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Temporada implements Serializable {

    private int numero;
    private int ano;

    private ArrayList<Episodio> episodios;

    public Temporada(int numero, int ano) {
        this.numero = numero;
        this.ano = ano;
        this.episodios = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public int getAno() {
        return ano;
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