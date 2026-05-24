package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Representa uma série na plataforma, composta por temporadas e episódios.
 * Herda de Recurso e implementa Serializable.
 * Categorias: Fraco (menor que 5), Médio (5 a 7.8), Bom (maior que 7.8).
 */


public class Serie extends Recurso implements Serializable {

    /** Lista de temporadas da série. */
    private ArrayList<Temporada> temporadas;

    /**
     * Constrói uma série com título e ano.
     * @param titulo título da série
     * @param ano    ano de estreia
     */

    public Serie(String titulo, int ano) {
        super(titulo, ano);
        this.temporadas = new ArrayList<>();
    }

    /**
     * Adiciona uma temporada à série.
     * @param temporada temporada a adicionar
     */

    public void adicionarTemporada(Temporada temporada) {
        temporadas.add(temporada);
    }

    /**
     * Devolve a lista de temporadas.
     * @return lista de temporadas
     */

    public ArrayList<Temporada> getTemporadas() {
        return temporadas;
    }

    /**
     * Pesquisa uma temporada pelo número.
     * @param numero número da temporada
     * @return temporada encontrada ou null se não existir
     */

    public Temporada getTemporada(int numero) {
        for (Temporada t : temporadas) {
            if (t.getNumero() == numero) return t;
        }
        return null;
    }


    /**
     * Devolve todos os episódios de todas as temporadas.
     * @return lista de todos os episódios
     */

    public ArrayList<Episodio> getTodosEpisodios() {
        ArrayList<Episodio> todos = new ArrayList<>();
        for (Temporada t : temporadas) {
            todos.addAll(t.getEpisodios());
        }
        return todos;
    }

    /**
     * Calcula a classificação média de todos os episódios da série.
     * @return média global ou 0.0 se sem classificações
     */

    @Override
    public double getClassificacaoMedia() {
        ArrayList<Episodio> todos = getTodosEpisodios();
        if (todos.isEmpty()) return 0.0;
        double soma = 0;
        int count = 0;
        for (Episodio e : todos) {
            if (!e.getClassificacoes().isEmpty()) {
                soma += e.getClassificacaoMedia();
                count++;
            }
        }
        if (count == 0) return 0.0;
        return soma / count;
    }


    /**
     * Devolve a categoria da classificação da série.
     * Fraco se menor que 5, Médio se 5 a 7.8, Bom se maior que 7.8.
     * @return categoria textual
     */

    @Override
    public String getCategoriaClassificacao() {
        double media = getClassificacaoMedia();
        if (media == 0.0) return "Sem classificação";
        if (media < 5) return "Fraco";
        if (media <= 7.8) return "Médio";
        return "Bom";
    }

    /**
     * Devolve uma representação textual da série.
     * @return título, ano, géneros e número de temporadas
     */

    @Override
    public String toString() {
        return "[Série] " + super.toString() + " | Temporadas: " + temporadas.size();
    }
}