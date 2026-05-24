package org.example.model;

public interface MarcavelComoVisto {

    // Verifica se o espectador já marcou como visto
    boolean isVisto(Espectador espectador);

    // Marca como visto — lança exceção se já foi marcado antes
    void marcarComoVisto(Espectador espectador) throws Exception;
}

//implementada por Filme e Episodio
//boolean verifica se o espectador já marcou como visto
//throws Exception - há situações em que o marcar como visto não deve ser permitido
//ex: já tinha sido marcado com visto
//em vez de ignorar, o método lança uma exceção com uma mensagem de erro