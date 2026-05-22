package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe que representa um Episódio de uma Série.
 */
public class Episodio implements MarcavelComoVisto, Pesquisavel, Serializable {

    private String titulo;
    private int numero;
    private int duracaoMinutos;
    private ArrayList<Espectador> espectadoresQueViram;
    private ArrayList<Classificacao> classificacoes;
    private ArrayList<Comentario> comentarios;

    // NOVO: Lista de atores
    private ArrayList<Ator> atores;

    /**
     * Construtor da classe Episodio.
     * @param titulo Título do episódio.
     * @param numero Número do episódio.
     * @param duracaoMinutos Duração do episódio.
     */
    public Episodio(String titulo, int numero, int duracaoMinutos) {
        this.titulo = titulo;
        this.numero = numero;
        this.duracaoMinutos = duracaoMinutos;
        this.espectadoresQueViram = new ArrayList<>();
        this.classificacoes = new ArrayList<>();
        this.comentarios = new ArrayList<>();
        this.atores = new ArrayList<>(); // Inicialização
    }

    public String getTitulo() { return titulo; }
    public int getNumero() { return numero; }
    public int getDuracaoMinutos() { return duracaoMinutos; }
    public ArrayList<Classificacao> getClassificacoes() { return classificacoes; }
    public ArrayList<Ator> getAtores() { return atores; }

    /**
     * Adiciona um ator ao episódio.
     * @param ator Ator a adicionar.
     */
    public void adicionarAtor(Ator ator) {
        if (!atores.contains(ator)) {
            atores.add(ator);
        }
    }

    /**
     * Adiciona uma classificação ao episódio (Garante regras de negócio).
     * @param classificacao Classificação do espetador.
     * @throws Exception Se o espetador não viu o episódio ou já o classificou.
     */
    public void adicionarClassificacao(Classificacao classificacao) throws Exception {
        Espectador e = classificacao.getEspectador();

        // NOVO: Validações exigidas
        if (!isVisto(e)) {
            throw new Exception("Erro: Não pode classificar um episódio que ainda não viu.");
        }
        if (jaClassificou(e)) {
            throw new Exception("Erro: Já classificou este episódio.");
        }
        classificacoes.add(classificacao);
    }

    public void adicionarComentario(Comentario comentario) {
        comentarios.add(comentario);
    }

    public boolean jaClassificou(Espectador espectador) {
        for (Classificacao c : classificacoes) {
            if (c.isDoEspectador(espectador)) return true;
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

    @Override
    public void marcarComoVisto(Espectador espectador) throws Exception {
        if (isVisto(espectador)) {
            throw new Exception("Este episódio já foi marcado como visto por este espectador.");
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
//implements MarcavelComoVisto - obriga a ter os métodos isVisto e marcarComoVisto
//espectadoresQueViram - lista para guardar todos os utilizadores que já assistiram ao episódio
//throws Exception - se o espetador já estiver na lista, bloqueia a ação e lança o erro