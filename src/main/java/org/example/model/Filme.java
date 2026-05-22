package org.example.model;

import java.util.ArrayList;

/**
 * Representa um Filme no sistema, permitindo interações como classificações,
 * comentários e marcação de visualizações.
 */
public class Filme extends Recurso implements MarcavelComoVisto {

    private int duracao;
    private ArrayList<Classificacao> classificacoes;
    private ArrayList<Comentario> comentarios;
    private ArrayList<Espectador> vistos;

    // NOVO: Lista de atores e valores de limiar dinâmicos para classificação
    private ArrayList<Ator> atores;
    private static double LIMIAR_MEDIO = 4.0;
    private static double LIMIAR_BOM = 8.0;

    /**
     * Construtor da classe Filme.
     * @param titulo Título do filme.
     * @param ano Ano de lançamento.
     * @param duracao Duração em minutos.
     */
    public Filme(String titulo, int ano, int duracao) {
        super(titulo, ano); // Chama o construtor de Recurso
        this.duracao = duracao;
        this.classificacoes = new ArrayList<>();
        this.comentarios = new ArrayList<>();
        this.vistos = new ArrayList<>();
        this.atores = new ArrayList<>(); // Inicialização da lista de atores
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

    public ArrayList<Ator> getAtores() {
        return atores;
    }

    /**
     * Adiciona um ator ao filme. O enunciado exige pelo menos 1 ator.
     * @param ator O ator a ser adicionado.
     */
    public void adicionarAtor(Ator ator) {
        if (!atores.contains(ator)) {
            atores.add(ator);
        }
    }

    /**
     * Adiciona uma classificação ao filme com base nas regras de negócio.
     * @param classificacao A classificação a adicionar.
     * @throws Exception Se o espetador não tiver visto o filme ou se já o tiver classificado.
     */
    public void adicionarClassificacao(Classificacao classificacao) throws Exception {
        Espectador e = classificacao.getEspectador();

        // NOVO: Validações exigidas pelo enunciado
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
            if (c.isDoEspectador(espectador)){
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
        for (Classificacao c : classificacoes) {
            soma += c.getValor();
        }
        return soma / classificacoes.size();
    }

    /**
     * Devolve a categoria da classificação do filme.
     * @return String "Fraco", "Médio" ou "Bom".
     */
    @Override
    public String getCategoriaClassificacao() {
        double media = getClassificacaoMedia();
        if (media == 0.0){
            return "Sem classificação";
        }
        // NOVO: Utilização de constantes dinâmicas em vez de "magic numbers"
        if (media < LIMIAR_MEDIO) {
            return "Fraco";
        }
        if (media <= LIMIAR_BOM){
            return "Médio";
        }
        return "Bom";
    }

    @Override
    public boolean isVisto(Espectador espectador) {
        return vistos.contains(espectador);
    }

    /**
     * Marca o filme como visto por um espetador.
     * @param espectador O utilizador que visualizou.
     * @throws Exception Se o filme já estiver marcado como visto por este espetador.
     */
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