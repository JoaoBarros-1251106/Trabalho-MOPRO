package org.example.model;

public class Classificacao {

    private int valor;
    private UtilizadorRegistado utilizador; // Mudou de Espectador para UtilizadorRegistado

    public Classificacao(int valor, UtilizadorRegistado utilizador) {
        this.valor = valor;
        this.utilizador = utilizador;
    }

    public int getValor() {
        return valor;
    }

    // Devolve o utilizador que classificou (Resolve o erro da linha 75)
    public UtilizadorRegistado getUtilizador() {
        return utilizador;
    }

    // Verifica se a classificação foi feita pelo utilizador indicado
    public boolean isDoUtilizador(UtilizadorRegistado u) {
        return this.utilizador.equals(u);
    }

    @Override
    public String toString() {
        return utilizador.getNome() + ": " + valor + "/10";
    }
}