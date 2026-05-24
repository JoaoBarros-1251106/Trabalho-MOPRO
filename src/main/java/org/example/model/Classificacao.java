package org.example.model;

import java.io.Serializable;

/**
 * Representa uma classificação atribuída por um espectador a um filme ou episódio.
 * Implementa Serializable para persistência em ficheiro.
 */
public class Classificacao implements Serializable {

    /**
     * Valor da classificação (1 a 10).
     */
    private int valor;

    /**
     * Espectador que atribuiu a classificação.
     */
    private Espectador espectador;

    /**
     * Constrói uma classificação.
     *
     * @param valor      Valor da classificação (1 a 10).
     * @param espectador Espectador que atribuiu a classificação.
     */
    public Classificacao(int valor, Espectador espectador) {
        this.valor = valor;
        this.espectador = espectador;
    }

    /**
     * Devolve o valor da classificação.
     *
     * @return Valor da classificação (1 a 10).
     */
    public int getValor() {
        return valor;
    }

    /**
     * Devolve o espectador que atribuiu a classificação
     *
     * @return Espectador que atribuiu a classificação.
     */
    public Espectador getEspectador() {
        return espectador;
    }

    /**
     * Verifica se a classificação foi feita pelo espectador indicado
     *
     * @param espectador espectador a verificar
     * @return true se for o mesmo espectador
     */
    public boolean isDoEspectador(Espectador espectador) {
        return this.espectador.equals(espectador);
    }

    /**
     * Devolve uma representação textual da classificação.
     *
     * @return String no formato "Nome do Espectador: Valor/10".
     */
    @Override
    public String toString() {
        return espectador.getNome() + ": " + valor + "/10";
    }
}