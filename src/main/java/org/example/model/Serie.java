package org.example.model;

import java.util.ArrayList;

public class Serie extends Recurso implements MarcavelComoVisto, Pesquisavel {

    private ArrayList<Temporada> temporadas;

    public Serie(String titulo, int ano) {
        // Chama o construtor da classe mãe (Recurso)
        super(titulo, ano);
        this.temporadas = new ArrayList<>();
    }

    public void adicionarTemporada(Temporada t) {
        temporadas.add(t);
    }

    public ArrayList<Temporada> getTemporadas() {
        return temporadas;
    }

    // --- Implementação da Interface Pesquisavel ---
    @Override
    public boolean correspondePesquisa(String texto) {
        // Usa o getTitulo() herdado de Recurso
        return getTitulo().toLowerCase().contains(texto.toLowerCase());
    }

    // --- Implementação da Interface MarcavelComoVisto ---
    @Override
    public boolean isVisto(Espectador espectador) {
        if (temporadas.isEmpty()) {
            return false;
        }
        // A série só está vista se TODAS as temporadas estiverem vistas
        for (Temporada t : temporadas) {
            if (!t.isVisto(espectador)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void marcarComoVisto(Espectador espectador) throws Exception {
        boolean algumaMarcadaAgora = false;

        // Percorre todas as temporadas e marca as que ainda não foram vistas
        for (Temporada t : temporadas) {
            if (!t.isVisto(espectador)) {
                t.marcarComoVisto(espectador);
                algumaMarcadaAgora = true;
            }
        }

        // Se nenhuma temporada precisou de ser marcada, significa que a série já estava toda vista
        if (!algumaMarcadaAgora) {
            throw new Exception("Esta série já foi totalmente vista por este espectador.");
        }
    }

    @Override
    public String toString() {
        // super.toString() vai buscar o "Titulo (Ano)" da classe Recurso
        return super.toString() + " | Temporadas: " + temporadas.size();
    }
}
