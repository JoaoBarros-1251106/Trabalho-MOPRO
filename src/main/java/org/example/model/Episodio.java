package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Episodio implements MarcavelComoVisto, Pesquisavel, Serializable {

    private String titulo;
    private int numero;
    private int duracaoMinutos;
    private ArrayList<Espectador> espectadoresQueViram;
    private ArrayList<Classificacao> classificacoes;
    private ArrayList<Comentario> comentarios;
    private ArrayList<Ator> atores;

    public Episodio(String titulo, int numero, int duracaoMinutos) {
        this.titulo = titulo;
        this.numero = numero;
        this.duracaoMinutos = duracaoMinutos;
        this.espectadoresQueViram = new ArrayList<>();
        this.classificacoes = new ArrayList<>();
        this.comentarios = new ArrayList<>();
        this.atores = new ArrayList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public int getNumero() {
        return numero;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public ArrayList<Classificacao> getClassificacoes() {
        return classificacoes;
    }

    public ArrayList<Ator> getAtores() {
        return atores;
    }

    public void adicionarAtor(Ator ator) {
        if (!atores.contains(ator)) atores.add(ator);
    }

    public void adicionarClassificacao(Classificacao c) {
        classificacoes.add(c);
    }

    public void adicionarComentario(Comentario c) {
        comentarios.add(c);
    }

    public boolean jaClassificou(Espectador e) {
        for (Classificacao c : classificacoes) {
            if (c.isDoEspectador(e)) return true;
        }
        return false;
    }

    public double getClassificacaoMedia() {
        if (classificacoes.isEmpty()) return 0.0;
        double soma = 0;
        for (Classificacao c : classificacoes) {
            soma += c.getValor();
        }
        return soma / classificacoes.size();
    }

    @Override
    public boolean isVisto(Espectador espectador) {
        return espectadoresQueViram.contains(espectador);
    }

    // Marca como visto — lança exceção se já foi marcado antes
    @Override
    public void marcarComoVisto(Espectador espectador) throws Exception {
        if (isVisto(espectador)) {
            throw new Exception("Este episódio já foi marcado como visto.");
        }
        espectadoresQueViram.add(espectador);
    }

    @Override
    public boolean correspondePesquisa(String texto) {
        return titulo.toLowerCase().contains(texto.toLowerCase());
    }

    @Override
    public String toString() {
        return "Episódio " + numero + " - " + titulo + " (" + duracaoMinutos + " min)";
    }
}