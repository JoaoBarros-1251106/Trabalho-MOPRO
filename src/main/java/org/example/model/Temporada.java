package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Representa uma temporada de uma série, composta por episódios.
 * Implementa Serializable para persistência em ficheiro.
 */


public class Temporada implements Serializable {
    /** Número da temporada. */
    private int numero;
    /** Ano de estreia da temporada. */
    private int ano;

    /** Lista de episódios da temporada. */
    private ArrayList<Episodio> episodios;

    /**
     * Constrói uma temporada.
     * @param numero número da temporada
     * @param ano    ano de estreia
     */

    public Temporada(int numero, int ano) {
        this.numero = numero;
        this.ano = ano;
        this.episodios = new ArrayList<>();
    }

    /**
     * Devolve o número da temporada.
     * @return número
     */

    public int getNumero() {
        return numero;
    }

    /**
     * Devolve o ano de estreia.
     * @return ano
     */

    public int getAno() {
        return ano;
    }

    /**
     * Adiciona um episódio à temporada (se ainda não existir).
     * @param episodio episódio a adicionar
     */

    public void adicionarEpisodio(Episodio episodio) {
        if (!episodios.contains(episodio)) {
            episodios.add(episodio);
        }
    }

    /**
     * Remove um episódio da temporada.
     * @param episodio episódio a remover
     */

    public void removerEpisodio(Episodio episodio) {
        episodios.remove(episodio);
    }

    /**
     * Devolve a lista de episódios da temporada.
     * @return lista de episódios
     */

    public ArrayList<Episodio> getEpisodios() {
        // Retorna uma cópia da lista para garantir segurança (Encapsulamento)
        return new ArrayList<>(episodios);
    }

    /**
     * Pesquisa um episódio pelo número.
     * @param numeroEpisodio número do episódio
     * @return episódio encontrado ou null se não existir
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
     * Calcula a duração total da temporada em minutos.
     * @return duração total em minutos
     */

    public int getDuracaoTotal() {
        int totalMinutos = 0;
        for (Episodio e : episodios) {
            totalMinutos += e.getDuracaoMinutos();
        }
        return totalMinutos;
    }

    /**
     * Devolve uma representação textual da temporada.
     * @return número, ano, episódios e duração total
     */

    @Override
    public String toString() {
        return "Temporada " + numero + " (" + ano + ") | Episódios: " + episodios.size() + " | Duração total: " + getDuracaoTotal() + " min";
    }
}

//numero - indica o número da temporada (1, 2, 3...)
//ArrayList<Episodio> - cada temporada contém a sua própria lista de episódios
//contains - tal como fizeste na ListaPessoal, usamos para evitar adicionar o mesmo episódio duas vezes