package org.example.model;

public interface MarcavelComoVisto {
    boolean isVisto(UtilizadorRegistado utilizador);
    void marcarComoVisto(UtilizadorRegistado utilizador);
}
//implementada por Filme e Episodio
//boolean verifica se o espcatador já marcou como visto
//throws Exception - há situações em que o marcar como visto não deve ser permitido
//ex: já tinha sido marcado com visto
//em vez de ignorar, o metodo lança uma exceçao com uma mensagem de erro