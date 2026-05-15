package model;

import org.example.model.MarcavelComoVisto;

import java.util.ArrayList;

public class Serie extends Recurso implements MarcavelComoVisto, Pesquisavel {

    private ArrayList<Temporada> temporadas;

    public Serie(String titulo, int ano) {

        super(titulo, ano);

        temporadas = new ArrayList<>();
    }

    public void adicionarTemporada(Temporada t) {

        temporadas.add(t);

    }

    public ArrayList<Temporada> getTemporadas() {
        return temporadas;
    }

    @Override
    public boolean pesquisar(String texto) {

        return getTitulo().toLowerCase()
                .contains(texto.toLowerCase());

    }

    @Override
    public String toString() {

        return super.toString() +
                " | Temporadas: " +
                temporadas.size();

    }

