package org.example.model;

import java.util.ArrayList;

public class Episodio implements MarcavelComoVisto {

    private String titulo;
    private int numero;
    private int duracaoMinutos;
    private ArrayList<Espectador> espectadoresQueViram;

    public Episodio(String titulo, int numero, int duracaoMinutos) {
        this.titulo = titulo;
        this.numero = numero;
        this.duracaoMinutos = duracaoMinutos;
        this.espectadoresQueViram = new ArrayList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public int getNumero() {
        return numero;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    // --- Implementação da Interface MarcavelComoVisto ---

    @Override
    public boolean isVisto(Espectador espectador) {
        return espectadoresQueViram.contains(espectador);
    }

    @Override
    public void marcarComoVisto(Espectador espectador) throws Exception {
        if (isVisto(espectador)) {
            // Lança a exceção tal como pedido nos comentários do teu ficheiro MarcavelComoVisto
            throw new Exception("Este episódio já foi marcado como visto por este espectador.");
        }
        espectadoresQueViram.add(espectador);
    }

    @Override
    public String toString() {
        return "Episódio " + numero + " - " + titulo + " (" + duracaoMinutos + " min)";
    }
}

//implements MarcavelComoVisto - obriga a ter os métodos isVisto e marcarComoVisto
//espectadoresQueViram - lista para guardar todos os utilizadores que já assistiram ao episódio
//throws Exception - se o espetador já estiver na lista, bloqueia a ação e lança o erro