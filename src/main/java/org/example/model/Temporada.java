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
        // Retorna uma cópia da lista para garantir segurança (Encapsulamento)
        return new ArrayList<>(episodios);
    }

    // ==========================================
    // MÉTODOS EXTRA PARA FACILITAR OS MENUS
    // ==========================================

    /**
     * Procura e devolve um episódio específico através do seu número.
     */
    public Episodio getEpisodio(int numeroEpisodio) {
        for (Episodio e : episodios) {
            if (e.getNumero() == numeroEpisodio) {
                return e;
            }
        }
        return null; // Retorna nulo se o episódio não existir nesta temporada
    }

    /**
     * Calcula a duração total da temporada somando a duração de todos os episódios.
     */
    public int getDuracaoTotal() {
        int totalMinutos = 0;
        for (Episodio e : episodios) {
            totalMinutos += e.getDuracaoMinutos();
        }
        return totalMinutos;
    }

    @Override
    public String toString() {
        return "Temporada " + numero + " (" + ano + ") | Episódios: " + episodios.size() + " | Duração total: " + getDuracaoTotal() + " min";
    }
}

//numero - indica o número da temporada (1, 2, 3...)
//ArrayList<Episodio> - cada temporada contém a sua própria lista de episódios
//contains - tal como fizeste na ListaPessoal, usamos para evitar adicionar o mesmo episódio duas vezes