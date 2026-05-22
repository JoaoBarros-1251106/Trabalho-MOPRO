package org.example.model;

public class Comentario {

    private UtilizadorRegistado utilizador; // Mudou de Espectador para UtilizadorRegistado
    private String texto;

    public Comentario(UtilizadorRegistado utilizador, String texto) {
        this.utilizador = utilizador;
        this.texto = texto;
    }

    public UtilizadorRegistado getUtilizador() {
        return utilizador;
    }

    public String getTexto() {
        return texto;
    }

    @Override
    public String toString() {
        return utilizador.getNome() + ": " + texto;
    }
}
