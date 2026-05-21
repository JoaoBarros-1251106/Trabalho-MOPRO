package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class DB {
    private String url;

    private List<UtilizadorRegistado> lstUtilizadores;
    private List<Ator> lstAtores;
    // adicionado
    private List<Filme> lstFilmes;
    private List<Serie> lstSeries;

    public DB(String url) {
        this.url = url;
        this.lstAtores = new ArrayList<Ator>();
        this.lstUtilizadores = new ArrayList<>();
        // adicionar
        this.lstFilmes = new ArrayList<>();
        this.lstSeries = new ArrayList<>();
    }

    public void adicionarAtor(Ator a) {
        this.lstAtores.add(a);
    }

    public void adicionarUtilizador(UtilizadorRegistado u) {
        this.lstUtilizadores.add(u);
    }

    public void removerAtor(Ator ator) {
        lstAtores.remove(ator);
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("=== Estado atual da DB ===").append("\n");
        sb.append("(").append(url).append(")");
        sb.append(listarUtilizadores());
        sb.append(listarAtores());
        // adicionado
        sb.append(listarFilmes());
        sb.append(listarSeries());
        return sb.toString();

        //criar claases listarfilmes e listar series
    }

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

    public Ator pesquisaAtor(String nome) {
        for (Ator a : lstAtores) {
            if (a.temNome(nome)) {
                return a;
            }
        }
        return null;
    }

    // Completar com outras funcionalidades
}
