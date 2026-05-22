package org.example.model;

import java.util.ArrayList;

/**
 * Classe que representa um Episódio de uma Série.
 */
public class Episodio implements MarcavelComoVisto, Pesquisavel {

    private String titulo;
    private int numero;
    private int duracaoMinutos;

    private ArrayList<UtilizadorRegistado> vistos; // Atualizado para usar UtilizadorRegistado
    private ArrayList<Classificacao> classificacoes;
    private ArrayList<Comentario> comentarios;
    private ArrayList<Ator> atores;

    /**
     * Construtor da classe Episodio.
     * @param titulo Título do episódio.
     * @param numero Número do episódio.
     * @param duracaoMinutos Duração do episódio.
     * @param ator Primeiro ator associado obrigatório.
     */
    public Episodio(String titulo, int numero, int duracaoMinutos, Ator ator) {
        this.titulo = titulo;
        this.numero = numero;
        this.duracaoMinutos = duracaoMinutos;
        this.vistos = new ArrayList<>();
        this.classificacoes = new ArrayList<>();
        this.comentarios = new ArrayList<>();
        this.atores = new ArrayList<>();
        this.atores.add(ator); // Garante que tem pelo menos 1 ator
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
        return new ArrayList<>(classificacoes);
    }

    public ArrayList<Ator> getAtores() {
        return new ArrayList<>(atores);
    }

    public void adicionarAtor(Ator ator) {
        if (!atores.contains(ator)) {
            atores.add(ator);
        }
    }

    public void adicionarClassificacao(Classificacao classificacao) {
        classificacoes.add(classificacao);
    }

    public void adicionarComentario(Comentario comentario) {
        comentarios.add(comentario);
    }

    public double getClassificacaoMedia() {
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
    // INTERFACE MarcavelComoVisto
    // ==========================================

    @Override
    public void marcarComoVisto(UtilizadorRegistado utilizador) {
        if (!vistos.contains(utilizador)) {
            vistos.add(utilizador);
        }
    }

    @Override
    public boolean isVisto(UtilizadorRegistado utilizador) {
        return vistos.contains(utilizador);
    }

    // ==========================================
    // INTERFACE Pesquisavel
    // ==========================================

    @Override
    public boolean correspondePesquisa(String texto) {
        return titulo.toLowerCase().contains(texto.toLowerCase());
    }

    @Override
    public String toString() {
        return "Episódio " + numero + " - " + titulo + " (" + duracaoMinutos + " min)";
    }
}
//implements MarcavelComoVisto - obriga a ter os métodos isVisto e marcarComoVisto
//espectadoresQueViram - lista para guardar todos os utilizadores que já assistiram ao episódio
//throws Exception - se o espetador já estiver na lista, bloqueia a ação e lança o erro