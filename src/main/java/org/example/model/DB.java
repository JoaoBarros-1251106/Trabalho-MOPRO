package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class DB {
    private String url;

    private List<UtilizadorRegistado> lstUtilizadores;
    private List<Ator> lstAtores;
    private List<Filme> lstFilmes;
    private List<Serie> lstSeries;

    public DB(String url) {
        this.url = url;
        this.lstAtores = new ArrayList<>();
        this.lstUtilizadores = new ArrayList<>();
        this.lstFilmes = new ArrayList<>();
        this.lstSeries = new ArrayList<>();
    }

    // ==========================================
    // MÉTODOS PARA ATORES
    // ==========================================
    public void adicionarAtor(Ator a) {
        if (!lstAtores.contains(a)) {
            this.lstAtores.add(a);
        } else {
            System.out.println("Erro: O ator já está registado.");
        }
    }

    public void removerAtor(Ator ator) {
        lstAtores.remove(ator);
    }

    public ArrayList<Ator> getAtores() {
        return new ArrayList<>(lstAtores);
    }

    public Ator pesquisaAtor(String nome) {
        for (Ator a : lstAtores) {
            if (a.temNome(nome)) {
                return a;
            }
        }
        return null;
    }

    // ==========================================
    // MÉTODOS PARA UTILIZADORES
    // ==========================================
    public void adicionarUtilizador(UtilizadorRegistado u) {
        if (!lstUtilizadores.contains(u)) {
            this.lstUtilizadores.add(u);
        } else {
            System.out.println("Erro: O utilizador já está registado.");
        }
    }

    public ArrayList<UtilizadorRegistado> getUtilizadores() {
        return new ArrayList<>(lstUtilizadores);
    }

    public UtilizadorRegistado pesquisaUtilizador(String username) {
        for (UtilizadorRegistado u : lstUtilizadores) {
            if (u.temNome(username)) {
                return u;
            }
        }
        return null;
    }

    public UtilizadorRegistado login(String username, String password) {
        UtilizadorRegistado ur = pesquisaUtilizador(username);
        if (ur != null && ur.temPassword(password))
            return ur;
        return null;
    }

    // ==========================================
    // MÉTODOS PARA FILMES
    // ==========================================
    public void adicionarFilme(Filme filme) {
        if (!lstFilmes.contains(filme)) {
            lstFilmes.add(filme);
        } else {
            System.out.println("Erro: O filme já existe no sistema.");
        }
    }

    public void removerFilme(Filme filme) {
        lstFilmes.remove(filme);
    }

    public ArrayList<Filme> getFilmes() {
        return new ArrayList<>(lstFilmes);
    }

    // ==========================================
    // MÉTODOS PARA SÉRIES
    // ==========================================
    public void adicionarSerie(Serie serie) {
        if (!lstSeries.contains(serie)) {
            lstSeries.add(serie);
        } else {
            System.out.println("Erro: A série já existe no sistema.");
        }
    }

    public ArrayList<Serie> getSeries() {
        return new ArrayList<>(lstSeries);
    }

    // ==========================================
    // MÉTODOS DE LISTAGEM (Para o toString)
    // ==========================================
    public String listarUtilizadores() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nLista de Utilizadores:");
        if (lstUtilizadores.isEmpty()) {
            sb.append(" (VAZIA)\n");
        } else {
            for (UtilizadorRegistado u : lstUtilizadores) {
                sb.append("\n\t- ").append(u).append(u instanceof Admin ? " (admin)" : "");
            }
        }
        return sb.toString();
    }

    public String listarAtores() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nLista de Atores:");
        if (lstAtores.isEmpty()) {
            sb.append(" (VAZIA)\n");
        } else {
            for (Ator ator : lstAtores) {
                sb.append("\n\t- ").append(ator);
            }
        }
        return sb.toString();
    }

    public String listarFilmes() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nLista de Filmes:");
        if (lstFilmes.isEmpty()) {
            sb.append(" (VAZIA)\n");
        } else {
            for (Filme f : lstFilmes) {
                sb.append("\n\t- ").append(f);
            }
        }
        return sb.toString();
    }

    public String listarSeries() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nLista de Séries:");
        if (lstSeries.isEmpty()) {
            sb.append(" (VAZIA)\n");
        } else {
            for (Serie s : lstSeries) {
                sb.append("\n\t- ").append(s);
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("=== Estado atual da DB ===\n");
        sb.append("(").append(url).append(")");
        sb.append(listarUtilizadores());
        sb.append(listarAtores());
        sb.append(listarFilmes());
        sb.append(listarSeries());
        return sb.toString();
    }
}