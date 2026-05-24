package org.example.model;

/**
 * Interface que define o contrato para marcar conteúdo como visto.
 * Implementada por Filme e Episodio.
 */

public interface MarcavelComoVisto {

    /**
     * Verifica se o espectador já marcou este conteúdo como visto.
     * @param espectador espectador a verificar
     * @return true se já foi visto
     */

    boolean isVisto(Espectador espectador);

    /**
     * Marca este conteúdo como visto pelo espectador.
     * @param espectador espectador que viu o conteúdo
     * @throws Exception se já foi marcado como visto
     */

    void marcarComoVisto(Espectador espectador) throws Exception;
}

