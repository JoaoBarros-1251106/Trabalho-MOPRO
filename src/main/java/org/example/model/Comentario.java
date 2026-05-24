package org.example.model;

import java.io.Serializable;

/**
 * Representa um comentário escrito por um espectador sobre um filme ou episódio.
 * Implementa Serializable para persistência em ficheiro.
 */
public class Comentario implements Serializable {

    /**
     * Espectador que fez o comentário. Assumimos que cada espectador tem um nome único para identificação.
     */
    private Espectador espectador;

    /**
     * Texto do comentário escrito pelo espectador.
     */
    private String texto;

    /**
     * Construtor para criar um comentário.
     *
     * @param espectador Espectador que fez o comentário.
     * @param texto      Texto do comentário.
     */
    public Comentario(Espectador espectador, String texto) {
        this.espectador = espectador;
        this.texto = texto;
    }

    /**
     * Devolve o espectador que fez o comentário.
     *
     * @return Espectador que fez o comentário.
     */
    public Espectador getEspectador() {
        return espectador;
    }

    /**
     * Devolve o texto do comentário.
     *
     * @return Texto do comentário.
     */
    public String getTexto() {
        return texto;
    }

    /**
     * Devolve uma representação em string do comentário, incluindo o nome do espectador e o texto do comentário.
     *
     * @return Representação em string do comentário no formato "Espectador: Texto do comentário".
     */
    @Override
    public String toString() {
        return espectador.getNome() + ": " + texto;
    }
}