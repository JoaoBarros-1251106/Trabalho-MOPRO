package org.example.model;

import java.io.Serializable;

public class Classificacao implements Serializable {

    private int valor;
    private Espectador espectador;

    public Classificacao(int valor, Espectador espectador) {
        this.valor = valor;
        this.espectador = espectador;
    }

    public int getValor() {
        return valor;
    }

    public Espectador getEspectador() {
        return espectador;
    }

    // Verifica se a classificação foi feita pelo espectador indicado
    public boolean isDoEspectador(Espectador e) {
        return this.espectador.equals(e);
    }

    @Override
    public String toString() {
        return espectador.getNome() + ": " + valor + "/10";
    }
}