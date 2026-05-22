package org.example.model;

import java.util.ArrayList;

/**
 * Representa um Filme no sistema, permitindo classificações,
 * comentários e marcação de visualizações.
 */
public class Filme extends Recurso implements MarcavelComoVisto {

    private int duracao;
    private ArrayList<Classificacao> classificacoes;
    private ArrayList<Comentario> comentarios;
    private ArrayList<Espectador> vistos;

    // Constantes para os limiares de classificação (evita magic numbers)
    private static final double LIMIAR_MEDIO = 4.0;
    private static final double LIMIAR_BOM = 8.0;

    /**
     * Construtor da classe Filme.
     * @param titulo Título do filme.
     * @param ano Ano de lançamento.
     * @param duracao Duração em minutos.
     * @param genero Primeiro género obrigatório.
     * @param ator Primeiro ator associado obrigatório (Regra do enunciado).
     */
    public Filme(String titulo, int ano, int duracao, Genero genero, Ator ator) {
        super(titulo, ano, genero); // Passa os dados para a classe mãe (Recurso)
        this.adicionarAtor(ator);   // Garante que tem pelo menos 1 ator
        this.duracao = duracao;
        this.classificacoes = new ArrayList<>();
        this.comentarios = new ArrayList<>();
        this.vistos = new ArrayList<>();
    }

    public int getDuracao() { return duracao; }
    public ArrayList<Classificacao> getClassificacoes() { return classificacoes; }
    public ArrayList<Comentario> getComentarios() { return comentarios; }

    /**
     * Adiciona uma classificação ao filme cumprindo as regras de negócio.
     * @param classificacao A classificação do espetador.
     * @throws Exception Se o filme não foi visto ou já foi classificado.
     */
    public void adicionarClassificacao(Classificacao classificacao) throws Exception {
        Espectador e = classificacao.getEspectador();
        if (!isVisto(e)) {
            throw new Exception("Erro: O espectador " + e.getNome() + " não pode classificar um filme que ainda não viu.");
        }
        if (jaClassificou(e)) {
            throw new Exception("Erro: O espectador " + e.getNome() + " já classificou este filme.");
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

    @Override
    public double getClassificacaoMedia() {
        if (classificacoes.isEmpty()) return 0.0;
        double soma = 0;
        for (Classificacao c : classificacoes) {
            soma += c.getValor();
        }
        return soma / classificacoes.size();
    }

    @Override
    public String getCategoriaClassificacao() {
        double media = getClassificacaoMedia();
        if (media == 0.0) return "Sem classificação";
        if (media < LIMIAR_MEDIO) return "Fraco";
        if (media <= LIMIAR_BOM) return "Médio";
        return "Bom";
    }

    @Override
    public boolean isVisto(Espectador espectador) {
        return vistos.contains(espectador);
    }

    @Override
    public void marcarComoVisto(Espectador espectador) throws Exception {
        if (isVisto(espectador)) {
            throw new Exception("O filme '" + getTitulo() + "' já foi marcado como visto por '" + espectador.getNome() + "'.");
        }
        vistos.add(espectador);
    }

    @Override
    public String toString() {
        return "[Filme] " + super.toString() + " | Duração: " + duracao + " min";
    }
}