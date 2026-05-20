package org.example.model;

import java.util.ArrayList;

public class Filme extends Recurso implements MarcavelComoVisto {

    // Duração do filme em minutos
    private int duracao;

    // Lista de classificações atribuídas ao filme
    private ArrayList<Classificacao> classificacoes;

    // Lista de comentários sobre o filme
    private ArrayList<Comentario> comentarios;

    // Lista de espectadores que marcaram o filme como visto
    private ArrayList<Espectador> vistos;

    public Filme(String titulo, int ano, int duracao) {
        super(titulo, ano);
        this.duracao = duracao;
        this.classificacoes = new ArrayList<>();
        this.comentarios = new ArrayList<>();
        this.vistos = new ArrayList<>();
    }

    public int getDuracao() {
        return duracao;
    }

    public ArrayList<Classificacao> getClassificacoes() {
        return classificacoes;
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public void adicionarClassificacao(Classificacao classificacao) {
        classificacoes.add(classificacao);
    }

    public void adicionarComentario(Comentario comentario) {
        comentarios.add(comentario);
    }

    public boolean jaClassificou(Espectador espectador) {
        for (Classificacao classificacao : classificacoes) {
            if (classificacao.isDoEspectador(espectador)){
                return true;
            }
        }
        return false;
    }

    @Override
    public double getClassificacaoMedia() {
        if (classificacoes.isEmpty()){
            return 0.0;
        }
        double soma = 0;
        for (Classificacao classificacao : classificacoes) {
            soma += classificacao.getValor();
        }
        return soma / classificacoes.size();
    }

    // Fraco < 4, Médio entre 4 e 8, Bom > 8
    @Override
    public String getCategoriaClassificacao() {
        double media = getClassificacaoMedia();
        if (media == 0.0){
            return "Sem classificação";
        }
        if (media < 4) {
            return "Fraco";
        }
        if (media <= 8){
            return "Médio";
        }
        return "Bom";
    }

    @Override
    public boolean isVisto(Espectador espectador) {
        return vistos.contains(espectador);
    }

    // Marca como visto — lança exceção se já foi marcado antes
    @Override
    public void marcarComoVisto(Espectador espectador) throws Exception {
        if (isVisto(espectador)) {
            throw new Exception("O filme '" + getTitulo()
                    + "' já foi marcado como visto por '"
                    + espectador.getNome() + "'.");
        }
        vistos.add(espectador);
    }

    @Override
    public String toString() {
        return "[Filme] " + super.toString()
                + " | Duração: " + duracao + " min";
    }
}