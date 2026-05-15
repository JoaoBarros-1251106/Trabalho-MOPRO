package org.example.model;

public class Classificacao {

    private UtilizadorRegistado utilizador;

    private int valor;

    public Classificacao(
            UtilizadorRegistado utilizador,
            int valor) {

        this.utilizador = utilizador;
        this.valor = valor;
    }

    public UtilizadorRegistado getUtilizador() {
        return utilizador;
    }

    public int getValor() {
        return valor;
    }

    @Override
    public String toString() {

        return utilizador.getNome() +
                " -> " + valor;

    }
}