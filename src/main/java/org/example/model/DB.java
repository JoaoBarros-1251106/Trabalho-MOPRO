package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DB implements Serializable {

    private String url;
    private ArrayList<UtilizadorRegistado> lstUtilizadores;
    private ArrayList<Ator> lstAtores;
    private ArrayList<Recurso> lstRecursos;

    public DB(String url) {
        this.url = url;
        this.lstUtilizadores = new ArrayList<>();
        this.lstAtores = new ArrayList<>();
        this.lstRecursos = new ArrayList<>();
    }

    // ===== UTILIZADORES =====

    public void adicionarUtilizador(UtilizadorRegistado u) {
        lstUtilizadores.add(u);
    }

    public UtilizadorRegistado pesquisaUtilizador(String username) {
        for (UtilizadorRegistado u : lstUtilizadores) {
            if (u.temNome(username)) return u;
        }
        return null;
    }

    public UtilizadorRegistado login(String username, String password) {
        UtilizadorRegistado ur = pesquisaUtilizador(username);
        if (ur != null && ur.temPassword(password)) return ur;
        return null;
    }

    public ArrayList<UtilizadorRegistado> getLstUtilizadores() {
        return lstUtilizadores;
    }

    // ===== ATORES =====

    public void adicionarAtor(Ator a) {
        lstAtores.add(a);
    }

    public void removerAtor(Ator a) {
        lstAtores.remove(a);
    }

    public Ator pesquisaAtor(String nome) {
        for (Ator a : lstAtores) {
            if (a.temNome(nome)) return a;
        }
        return null;
    }

    public ArrayList<Ator> pesquisarAtores(String texto) {
        ArrayList<Ator> resultado = new ArrayList<>();
        for (Ator a : lstAtores) {
            if (a.correspondePesquisa(texto)) resultado.add(a);
        }
        return resultado;
    }

    public ArrayList<Ator> getLstAtores() {
        return lstAtores;
    }

    // ===== RECURSOS =====

    // Adiciona recurso — lança exceção se já existir igual
    public void adicionarRecurso(Recurso r) throws Exception {
        for (Recurso existente : lstRecursos) {
            if (existente.isDuplicado(r.getTitulo(), r.getAno())) {
                throw new Exception("Já existe um recurso com o título '"
                        + r.getTitulo() + "' e ano " + r.getAno() + ".");
            }
        }
        lstRecursos.add(r);
    }

    public void removerRecurso(Recurso r) {
        lstRecursos.remove(r);
    }

    public ArrayList<Recurso> pesquisarRecursos(String texto) {
        ArrayList<Recurso> resultado = new ArrayList<>();
        for (Recurso r : lstRecursos) {
            if (r.correspondePesquisa(texto)) resultado.add(r);
        }
        return resultado;
    }

    // Devolve só os filmes
    public ArrayList<Filme> getFilmes() {
        ArrayList<Filme> filmes = new ArrayList<>();
        for (Recurso r : lstRecursos) {
            if (r instanceof Filme) filmes.add((Filme) r);
        }
        return filmes;
    }

    // Devolve só as séries
    public ArrayList<Serie> getSeries() {
        ArrayList<Serie> series = new ArrayList<>();
        for (Recurso r : lstRecursos) {
            if (r instanceof Serie) series.add((Serie) r);
        }
        return series;
    }

    public ArrayList<Recurso> getLstRecursos() {
        return lstRecursos;
    }

    // ===== CLASSIFICAÇÕES =====

    public void classificarFilme(Filme f, Espectador e, int valor) throws Exception {
        if (valor < 1 || valor > 10)
            throw new Exception("A classificação deve estar entre 1 e 10.");
        if (!f.isVisto(e))
            throw new Exception("Só pode classificar um filme que já viu.");
        if (f.jaClassificou(e))
            throw new Exception("Já classificou este filme.");
        f.adicionarClassificacao(new Classificacao(valor, e));
    }

    public void classificarEpisodio(Episodio ep, Espectador e, int valor) throws Exception {
        if (valor < 1 || valor > 10)
            throw new Exception("A classificação deve estar entre 1 e 10.");
        if (!ep.isVisto(e))
            throw new Exception("Só pode classificar um episódio que já viu.");
        if (ep.jaClassificou(e))
            throw new Exception("Já classificou este episódio.");
        ep.adicionarClassificacao(new Classificacao(valor, e));
    }

    // ===== ORDENAÇÕES =====

    // Filmes ordenados por título (A-Z)
    public ArrayList<Filme> listarFilmesPorTitulo() {
        ArrayList<Filme> filmes = getFilmes();
        Collections.sort(filmes, new Comparator<Filme>() {
            @Override
            public int compare(Filme f1, Filme f2) {
                return f1.getTitulo().compareTo(f2.getTitulo());
            }
        });
        return filmes;
    }

    // Filmes ordenados por classificação (maior primeiro)
    public ArrayList<Filme> listarFilmesPorClassificacao() {
        ArrayList<Filme> filmes = getFilmes();
        Collections.sort(filmes, new Comparator<Filme>() {
            @Override
            public int compare(Filme f1, Filme f2) {
                return Double.compare(f2.getClassificacaoMedia(), f1.getClassificacaoMedia());
            }
        });
        return filmes;
    }

    // Atores ordenados por nome (A-Z)
    public ArrayList<Ator> listarAtoresPorNome() {
        ArrayList<Ator> atores = new ArrayList<>(lstAtores);
        Collections.sort(atores, new Comparator<Ator>() {
            @Override
            public int compare(Ator a1, Ator a2) {
                return a1.getNome().compareTo(a2.getNome());
            }
        });
        return atores;
    }

    // Atores ordenados por número de participações (maior primeiro)
    public ArrayList<Ator> listarAtoresPorNumFilmes() {
        ArrayList<Ator> atores = new ArrayList<>(lstAtores);
        Collections.sort(atores, new Comparator<Ator>() {
            @Override
            public int compare(Ator a1, Ator a2) {
                return Integer.compare(contarParticipacoes(a2), contarParticipacoes(a1));
            }
        });
        return atores;
    }

    // Conta participações de um ator em filmes e episódios
    private int contarParticipacoes(Ator ator) {
        int count = 0;
        for (Recurso r : lstRecursos) {
            if (r.getAtores().contains(ator)) count++;
            if (r instanceof Serie) {
                for (Episodio ep : ((Serie) r).getTodosEpisodios()) {
                    if (ep.getAtores().contains(ator)) count++;
                }
            }
        }
        return count;
    }

    // Espectadores ordenados por filmes vistos (maior primeiro)
    public ArrayList<Espectador> listarEspectadoresPorFilmesVistos() {
        ArrayList<Espectador> espectadores = new ArrayList<>();
        for (UtilizadorRegistado u : lstUtilizadores) {
            if (u instanceof Espectador) espectadores.add((Espectador) u);
        }
        Collections.sort(espectadores, new Comparator<Espectador>() {
            @Override
            public int compare(Espectador e1, Espectador e2) {
                return Integer.compare(contarFilmesVistos(e2), contarFilmesVistos(e1));
            }
        });
        return espectadores;
    }

    // Conta filmes vistos por um espectador
    private int contarFilmesVistos(Espectador e) {
        int count = 0;
        for (Filme f : getFilmes()) {
            if (f.isVisto(e)) count++;
        }
        return count;
    }

    // ===== LISTAGENS =====

    public String listarUtilizadores() {
        StringBuilder sb = new StringBuilder("\nLista de Utilizadores:");
        if (lstUtilizadores.isEmpty()) {
            sb.append(" (VAZIA)\n");
        } else {
            for (UtilizadorRegistado u : lstUtilizadores) {
                sb.append("\n\t- ").append(u);
                if (u instanceof Admin) sb.append(" (admin)");
            }
        }
        return sb.toString();
    }

    public String listarAtores() {
        StringBuilder sb = new StringBuilder("\nLista de Atores:");
        if (lstAtores.isEmpty()) {
            sb.append(" (VAZIA)\n");
        } else {
            for (Ator a : lstAtores) {
                sb.append("\n\t- ").append(a);
            }
        }
        return sb.toString();
    }

    public String listarRecursos() {
        StringBuilder sb = new StringBuilder("\nLista de Recursos:");
        if (lstRecursos.isEmpty()) {
            sb.append(" (VAZIA)\n");
        } else {
            for (Recurso r : lstRecursos) {
                sb.append("\n\t- ").append(r.getTitulo())
                        .append(" (").append(r.getAno()).append(")")
                        .append(" | ").append(r instanceof Filme ? "Filme" : "Série");
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
        sb.append(listarRecursos());
        return sb.toString();
    }
}