package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Representa um episódio de uma temporada de uma série.
 * Implementa MarcavelComoVisto, Pesquisavel e Serializable.
 */
public class Episodio implements MarcavelComoVisto, Pesquisavel, Serializable {

    /**
     * Atributos:
     * - titulo: título do episódio
     * - numero: número do episódio dentro da temporada
     * - duracaoMinutos: duração do episódio em minutos
     * - espectadoresQueViram: lista de espectadores que marcaram este episódio como visto
     * - classificacoes: lista de classificações dadas por espectadores
     * - comentarios: lista de comentários feitos por espectadores
     * - atores: lista de atores que participaram deste episódio
     */
    private String titulo;
    private int numero;
    private int duracaoMinutos;
    private ArrayList<Espectador> espectadoresQueViram;
    private ArrayList<Classificacao> classificacoes;
    private ArrayList<Comentario> comentarios;
    private ArrayList<Ator> atores;

    /**
     * Constrói um  novo episódio com o título, número e duração especificados.
     *
     * @param titulo         título do episódio
     * @param numero         número do episódio dentro da temporada
     * @param duracaoMinutos duração do episódio em minutos
     */
    public Episodio(String titulo, int numero, int duracaoMinutos) {
        this.titulo = titulo;
        this.numero = numero;
        this.duracaoMinutos = duracaoMinutos;
        this.espectadoresQueViram = new ArrayList<>();
        this.classificacoes = new ArrayList<>();
        this.comentarios = new ArrayList<>();
        this.atores = new ArrayList<>();
    }

    /**
     * Devolve o título do episódio.
     *
     * @return título do episódio
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Devolve o número do episódio dentro da temporada.
     *
     * @return número do episódio dentro da temporada
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Devolve a duração do episódio em minutos.
     *
     * @return duração do episódio em minutos
     */
    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    /**
     * Devolve a lista de classificações.
     *
     * @return lista de classificações dadas por espectadores para este episódio
     */
    public ArrayList<Classificacao> getClassificacoes() {
        return classificacoes;
    }

    /**
     * Devolve a lista de atores.
     *
     * @return lista de atores
     */
    public ArrayList<Ator> getAtores() {
        return atores;
    }

    /**
     * Associa um ator ao episódio.
     *
     * @param ator ator a associar
     */
    public void adicionarAtor(Ator ator) {
        if (!atores.contains(ator)) atores.add(ator);
    }

    /**
     * Adiciona uma classificação ao episódio.
     *
     * @param c classificação a adicionar
     */
    public void adicionarClassificacao(Classificacao c) {
        classificacoes.add(c);
    }

    /**
     * Adiciona um comentário ao episódio.
     *
     * @param c comentário a adicionar
     */
    public void adicionarComentario(Comentario c) {
        comentarios.add(c);
    }

    /**
     * Verifica se o espectador já classificou este episódio.
     *
     * @param espectador espectador a verificar
     * @return true se já classificou
     */
    public boolean jaClassificou(Espectador espectador) {
        for (Classificacao c : classificacoes) {
            if (c.isDoEspectador(espectador)) return true;
        }
        return false;
    }

    /**
     * Calcula a classificação média do episódio.
     *
     * @return média ou 0.0 se sem classificações
     */
    public double getClassificacaoMedia() {
        if (classificacoes.isEmpty()) return 0.0;
        double soma = 0;
        for (Classificacao c : classificacoes) {
            soma += c.getValor();
        }
        return soma / classificacoes.size();
    }

    /**
     * Verifica se o espectador já marcou este episódio como visto.
     *
     * @param espectador espectador a verificar
     * @return true se já viu
     */
    @Override
    public boolean isVisto(Espectador espectador) {
        return espectadoresQueViram.contains(espectador);
    }

    /**
     * Marca o episódio como visto pelo espectador.
     *
     * @param espectador espectador que viu o episódio
     * @throws Exception se o espectador já o marcou como visto
     */
    @Override
    public void marcarComoVisto(Espectador espectador) throws Exception {
        if (isVisto(espectador)) {
            throw new Exception("Este episódio já foi marcado como visto.");
        }
        espectadoresQueViram.add(espectador);
    }

    /**
     * Verifica se o título contém o texto de pesquisa.
     *
     * @param texto texto a pesquisar
     * @return true se o título contiver o texto
     */
    @Override
    public boolean correspondePesquisa(String texto) {
        return titulo.toLowerCase().contains(texto.toLowerCase());
    }

    /**
     * Devolve uma representação textual do episódio.
     *
     * @return número, título e duração
     */
    @Override
    public String toString() {
        return "Episódio " + numero + " - " + titulo + " (" + duracaoMinutos + " min)";
    }
}