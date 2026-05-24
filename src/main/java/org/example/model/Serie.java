package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Serie extends Recurso implements Serializable {
    // Lista de temporadas da série
    private ArrayList<Temporada> temporadas;

    public Serie(String titulo, int ano) {
        super(titulo, ano);
        this.temporadas = new ArrayList<>();
    }

    public void adicionarTemporada(Temporada temporada) {
        temporadas.add(temporada);
    }

    public ArrayList<Temporada> getTemporadas() {
        return temporadas;
    }

    // Pesquisa uma temporada pelo número
    public Temporada getTemporada(int numero) {
        for (Temporada t : temporadas) {
            if (t.getNumero() == numero) return t;
        }
        return null;
    }

    // Devolve todos os episódios de todas as temporadas
    public ArrayList<Episodio> getTodosEpisodios() {
        ArrayList<Episodio> todos = new ArrayList<>();
        for (Temporada t : temporadas) {
            todos.addAll(t.getEpisodios());
        }
        return todos;
    }

    // Calcula a média das classificações de todos os episódios
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

    // Fraco < 5, Médio entre 5 e 7.8, Bom > 7.8
    @Override
    public String getCategoriaClassificacao() {
        double media = getClassificacaoMedia();
        if (media == 0.0) return "Sem classificação";
        if (media < 5) return "Fraco";
        if (media <= 7.8) return "Médio";
        return "Bom";
    }

    @Override
    public String toString() {
        return "[Série] " + super.toString() + " | Temporadas: " + temporadas.size();
    }
}