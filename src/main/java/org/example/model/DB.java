package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Base de Dados em memória que centraliza a gestão de toda a informação.
 * Implementa Serializable para permitir guardar e carregar dados em ficheiro.
 */
public class DB implements Serializable { // NOVO: Implementação de Serializable

    // SerialVersionUID é recomendado para garantir compatibilidade ao ler/gravar o ficheiro
    private static final long serialVersionUID = 1L;

    private String url;
    private List<UtilizadorRegistado> lstUtilizadores;
    private List<Ator> lstAtores;
    private List<Filme> lstFilmes; // NOVO: Gestão da lista de Filmes

    /**
     * Inicializa a Base de Dados.
     * @param url Nome/Caminho representativo da base de dados.
     */
    public DB(String url) {
        this.url = url;
        this.lstAtores = new ArrayList<>();
        this.lstUtilizadores = new ArrayList<>();
        this.lstFilmes = new ArrayList<>(); // Inicialização
    }

    /**
     * Adiciona um ator à base de dados.
     * @param a O ator a adicionar.
     */
    public void adicionarAtor(Ator a) {
        this.lstAtores.add(a);
    }

    public void removerAtor(Ator ator) {
        lstAtores.remove(ator);
    }

    /**
     * Adiciona um utilizador à base de dados.
     * @param u Utilizador a adicionar.
     */
    public void adicionarUtilizador(UtilizadorRegistado u) {
        this.lstUtilizadores.add(u);
    }

    /**
     * Adiciona um filme garantindo que não existem duplicados.
     * @param filme O filme a adicionar.
     * @throws Exception Lança exceção se o filme já existir.
     */
    public void adicionarFilme(Filme filme) throws Exception {
        // Exige que a classe Recurso tenha o método equals() definido para comparar título + ano
        if (lstFilmes.contains(filme)) {
            throw new Exception("Erro: O filme '" + filme.getTitulo() + "' já existe na base de dados (título e ano duplicados).");
        }
        lstFilmes.add(filme);
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

    public Ator pesquisaAtor(String nome) {
        for (Ator a : lstAtores) {
            if (a.temNome(nome)) {
                return a;
            }
        }
        return null;
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

    /**
     * Devolve uma cópia da lista de filmes (para não quebrar o encapsulamento).
     * @return Lista de filmes registados.
     */
    public List<Filme> getFilmes() {
        return new ArrayList<>(lstFilmes);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("=== Estado atual da DB ===\n");
        sb.append("(").append(url).append(")");
        sb.append(listarUtilizadores());
        sb.append(listarAtores());
        sb.append("\nTotal de Filmes: ").append(lstFilmes.size());
        return sb.toString();
    }
}