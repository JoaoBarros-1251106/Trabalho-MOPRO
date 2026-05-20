package org.example.model;


import java.util.ArrayList;

public class Filme implements MarcavelComoVisto, Pesquisavel {

    private int duracao;

    private ArrayList<Comentario> comentarios;
    private ArrayList<Classificacao> classificacoes;

    public Filme(String titulo, int ano, int duracao) {

        super(titulo, ano);

        this.duracao = duracao;

        comentarios = new ArrayList<>();
        classificacoes = new ArrayList<>();
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

    public double calcularMedia() {

        if(classificacoes.isEmpty()) {
            return 0;
        }

        int soma = 0;

        for(Classificacao c : classificacoes) {

            soma += c.getValor();

        }

        return (double) soma / classificacoes.size();
    }

    @Override
    public void marcarComoVisto() {

        System.out.println("Filme marcado como visto.");

    }

    @Override
    public boolean pesquisar(String texto) {

        return getTitulo().toLowerCase()
                .contains(texto.toLowerCase());
    }

    @Override
    public String toString() {

        return super.toString() +
                " | Duracao: " + duracao;

    }
}