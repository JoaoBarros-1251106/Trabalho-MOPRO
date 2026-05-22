package org.example.model;

import java.util.ArrayList;

public class Filme extends Recurso implements MarcavelComoVisto {

    private int duracao;
    private ArrayList<Classificacao> classificacoes;
    private ArrayList<Comentario> comentarios;
    private ArrayList<UtilizadorRegistado> vistos;

    private static final double LIMIAR_MEDIO = 4.0;
    private static final double LIMIAR_BOM = 8.0;

    public Filme(String titulo, int ano, int duracao, Genero genero, Ator ator) {
        super(titulo, ano, genero);
        this.adicionarAtor(ator);
        this.duracao = duracao;
        this.classificacoes = new ArrayList<>();
        this.comentarios = new ArrayList<>();
        this.vistos = new ArrayList<>();
    }

    public int getDuracao() {
        return duracao;
    }

    public void adicionarComentario(Comentario comentario) {
        comentarios.add(comentario);
    }

    public void adicionarClassificacao(Classificacao classificacao) {
        classificacoes.add(classificacao);
    }

    public double calcularMediaClassificacoes() {
        if (classificacoes.isEmpty()) {
            return 0;
        }

        double soma = 0;

        for (Classificacao c : classificacoes) {
            soma += c.getValor();
        }

        return soma / classificacoes.size();
    }

    @Override
    public void marcarComoVisto(UtilizadorRegistado utilizador) {
        // Chama o método que já tínhamos criado no UtilizadorRegistado
        utilizador.adicionarVisualizado(this);
    }

    @Override
    public boolean isVisto(UtilizadorRegistado utilizador) {
        // Verifica na lista do UtilizadorRegistado se este filme já lá está
        return utilizador.jaViu(this);
    }

    @Override
    public double getClassificacaoMedia() {
        return calcularMediaClassificacoes();
    }

    @Override
    public String getCategoriaClassificacao() {
        double media = calcularMediaClassificacoes();
        if (media == 0.0) return "Sem classificação";
        if (media < LIMIAR_MEDIO) return "Fraco";
        if (media <= LIMIAR_BOM) return "Médio";
        return "Bom";
    }
    public ArrayList<Classificacao> getClassificacoes() {
        return new ArrayList<>(classificacoes);
    }

    @Override
    public String toString() {
        return "[Filme] " + super.toString() + " | Duração: " + duracao + " min";
    }
}