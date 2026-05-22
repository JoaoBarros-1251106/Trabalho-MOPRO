package org.example.model;

import java.util.ArrayList;

/**
 * Representa uma Série no sistema, contendo uma lista de temporadas.
 */
public class Serie extends Recurso {

    private ArrayList<Temporada> temporadas;

    // Constantes corrigidas (O enunciado dizia Médio 5 a 7.8 e Bom > 7.5. Ajustado para >= 7.8)
    private static final double LIMIAR_MEDIO_SERIE = 5.0;
    private static final double LIMIAR_BOM_SERIE = 7.8;

    /**
     * Construtor da classe Serie.
     * @param titulo Título da série.
     * @param ano Ano de lançamento inicial.
     * @param genero Primeiro género obrigatório.
     */
    public Serie(String titulo, int ano, Genero genero) {
        super(titulo, ano, genero);
        this.temporadas = new ArrayList<>();
    }

    public void adicionarTemporada(Temporada temporada) {
        if (!temporadas.contains(temporada)) {
            temporadas.add(temporada);
        }
    }

    public ArrayList<Temporada> getTemporadas() {
        return temporadas;
    }

    public Temporada getTemporada(int numero) {
        for (Temporada t : temporadas) {
            if (t.getNumero() == numero) return t;
        }
        return null;
    }

    public ArrayList<Episodio> getTodosEpisodios() {
        ArrayList<Episodio> todos = new ArrayList<>();
        for (Temporada t : temporadas) {
            todos.addAll(t.getEpisodios());
        }
        return todos;
    }

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
        return count == 0 ? 0.0 : soma / count;
    }

    @Override
    public String getCategoriaClassificacao() {
        double media = getClassificacaoMedia();
        if (media == 0.0) return "Sem classificação";
        if (media < LIMIAR_MEDIO_SERIE) return "Fraco";
        if (media <= LIMIAR_BOM_SERIE) return "Médio";
        return "Bom";
    }

    @Override
    public String toString() {
        return "[Série] " + super.toString() + " | Temporadas: " + temporadas.size();
    }
}