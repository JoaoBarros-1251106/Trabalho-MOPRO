package org.example.model;

import java.util.ArrayList;

public class Serie extends Recurso {

    private ArrayList<Temporada> temporadas;
    private ArrayList<Classificacao> classificacoes; // Adicionado conforme pediste

    private static final double LIMIAR_MEDIO_SERIE = 5.0;
    private static final double LIMIAR_BOM_SERIE = 7.8;

    public Serie(String titulo, int ano, Genero genero) {
        super(titulo, ano, genero); // O título, ano e género são enviados para o Recurso
        this.temporadas = new ArrayList<>();
        this.classificacoes = new ArrayList<>();
    }

    // ==========================================
    // GESTÃO DE TEMPORADAS
    // ==========================================

    public void adicionarTemporada(Temporada temporada) {
        if (!temporadas.contains(temporada)) {
            temporadas.add(temporada);
        }
    }

    public ArrayList<Temporada> getTemporadas() {
        return new ArrayList<>(temporadas); // Proteção da lista (encapsulamento)
    }

    // ==========================================
    // CLASSIFICAÇÕES
    // ==========================================

    public void adicionarClassificacao(Classificacao classificacao) {
        classificacoes.add(classificacao);
    }

    public double calcularMediaClassificacoes() {
        if (classificacoes.isEmpty()) {
            return 0.0;
        }

        double soma = 0;
        for (Classificacao c : classificacoes) {
            soma += c.getValor();
        }

        return soma / classificacoes.size();
    }

    // ==========================================
    // MÉTODOS HERDADOS DE RECURSO
    // ==========================================

    @Override
    public double getClassificacaoMedia() {
        return calcularMediaClassificacoes();
    }

    @Override
    public String getCategoriaClassificacao() {
        double media = calcularMediaClassificacoes();
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