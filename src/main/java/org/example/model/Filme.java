package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Representa um filme na plataforma.
 * Herda de Recurso e implementa MarcavelComoVisto e Serializable.
 * Categorias: Fraco (menor que 4), Médio (4 a 8), Bom (maior que 8).
 */

public class Filme extends Recurso implements MarcavelComoVisto, Serializable {

    /** Duração do filme em minutos. */
    private int duracao;

    /** Lista de classificações do filme. */
    private ArrayList<Classificacao> classificacoes;

    /** Lista de comentários do filme. */
    private ArrayList<Comentario> comentarios;

    /** Lista de espectadores que marcaram como visto. */
    private ArrayList<Espectador> vistos;

    /**
     * Constrói um filme.
     * @param titulo  título do filme
     * @param ano     ano de lançamento
     * @param duracao duração em minutos
     */

    public Filme(String titulo, int ano, int duracao) {
        super(titulo, ano);
        this.duracao = duracao;
        this.classificacoes = new ArrayList<>();
        this.comentarios = new ArrayList<>();
        this.vistos = new ArrayList<>();
    }


    /**
     * Devolve a duração do filme em minutos.
     * @return duração
     */


    public int getDuracao() {
        return duracao;
    }

    /**
     * Devolve a lista de classificações.
     * @return lista de classificações
     */


    public ArrayList<Classificacao> getClassificacoes() {
        return classificacoes;
    }

    /**
     * Devolve a lista de comentários.
     * @return lista de comentários
     */

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    /**
     * Adiciona uma classificação ao filme.
     * @param c classificação a adicionar
     */

    public void adicionarClassificacao(Classificacao c) {
        classificacoes.add(c);
    }

    /**
     * Adiciona um comentário ao filme.
     * @param c comentário a adicionar
     */


    public void adicionarComentario(Comentario c) {
        comentarios.add(c);
    }

    /**
     * Verifica se o espectador já classificou este filme.
     * @param e espectador a verificar
     * @return true se já classificou
     */

    public boolean jaClassificou(Espectador e) {
        for (Classificacao c : classificacoes) {
            if (c.isDoEspectador(e)) return true;
        }
        return false;
    }

    /**
     * Calcula a classificação média do filme.
     * @return média ou 0.0 se sem classificações
     */


    @Override
    public double getClassificacaoMedia() {
        if (classificacoes.isEmpty()) return 0.0;
        double soma = 0;
        for (Classificacao c : classificacoes) {
            soma += c.getValor();
        }
        return soma / classificacoes.size();
    }

    /**
     * Devolve a categoria da classificação do filme.
     * Fraco se menor que 4, Médio se 4 a 8, Bom se maior que 8.
     * @return categoria textual
     */

    @Override
    public String getCategoriaClassificacao() {
        double media = getClassificacaoMedia();
        if (media == 0.0) return "Sem classificação";
        if (media < 4) return "Fraco";
        if (media <= 8) return "Médio";
        return "Bom";
    }

    /**
     * Verifica se o espectador já marcou este filme como visto.
     * @param espectador espectador a verificar
     * @return true se já viu
     */

    @Override
    public boolean isVisto(Espectador espectador) {
        return vistos.contains(espectador);
    }

    /**
     * Marca o filme como visto pelo espectador.
     * @param espectador espectador que viu o filme
     * @throws Exception se o espectador já o marcou como visto
     */

    @Override
    public void marcarComoVisto(Espectador espectador) throws Exception {
        if (isVisto(espectador)) {
            throw new Exception("O filme '" + getTitulo()
                    + "' já foi marcado como visto por '"
                    + espectador.getNome() + "'.");
        }
        vistos.add(espectador);
    }

    /**
     * Devolve uma representação textual do filme.
     * @return título, ano, géneros, classificação e duração
     */

    @Override
    public String toString() {
        return "[Filme] " + super.toString()
                + " | Duração: " + duracao + " min";
    }
}