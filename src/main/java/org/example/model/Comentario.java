package org.example.model;

import java.io.Serializable;

public class Comentario implements Serializable {

    private Espectador espectador;
    private String texto;

    public Comentario(Espectador espectador, String texto) {
        this.espectador = espectador;
        this.texto = texto;
    }

    public Espectador getEspectador() {
        return espectador;
    }

    public String getTexto() {
        return texto;
    }

    @Override
    public String toString() {
        return espectador.getNome() + ": " + texto;
    }
}