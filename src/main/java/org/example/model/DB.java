package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Classe central da plataforma — guarda todas as listas e gere a lógica do sistema.
 * Implementa Serializable para persistência em ficheiro.
 */
public class DB implements Serializable {

    /**
     * URL da plataforma
     */
    private String url;
    /**
     * Listas de utilizadores, atores e recursos (filmes e séries)
     */
    private ArrayList<UtilizadorRegistado> lstUtilizadores;
    private ArrayList<Ator> lstAtores;
    private ArrayList<Recurso> lstRecursos;

    /**
     * Constrói a base de dados da plataforma.
     *
     * @param url URL da plataforma
     */
    public DB(String url) {
        this.url = url;
        this.lstUtilizadores = new ArrayList<>();
        this.lstAtores = new ArrayList<>();
        this.lstRecursos = new ArrayList<>();
    }

    // ===== UTILIZADORES =====

    /**
     * Adiciona um utilizador registado à plataforma.
     *
     * @param utilizadorRegistado
     */
    public void adicionarUtilizador(UtilizadorRegistado utilizadorRegistado) {
        lstUtilizadores.add(utilizadorRegistado);
    }

    /**
     * Pesquisa um utilizador pelo username.
     *
     * @param username username a pesquisar
     * @return utilizador encontrado ou null se não existir
     */
    public UtilizadorRegistado pesquisaUtilizador(String username) {
        for (UtilizadorRegistado u : lstUtilizadores) {
            if (u.temNome(username)) return u;
        }
        return null;
    }

    /**
     * Realiza o login de um utilizador.
     *
     * @param username username
     * @param password password
     * @return utilizador autenticado ou null se credenciais inválidas
     */
    public UtilizadorRegistado login(String username, String password) {
        UtilizadorRegistado ur = pesquisaUtilizador(username);
        if (ur != null && ur.temPassword(password)) return ur;
        return null;
    }

    /**
     * Devolve a lista de todos os utilizadores.
     *
     * @return lista de utilizadores
     */
    public ArrayList<UtilizadorRegistado> getLstUtilizadores() {
        return lstUtilizadores;
    }

    // ===== ATORES =====

    /**
     * Adiciona um ator à plataforma.
     *
     * @param a ator a adicionar
     */
    public void adicionarAtor(Ator a) {
        lstAtores.add(a);
    }

    /**
     * Remove um ator da plataforma.
     *
     * @param a ator a remover
     */
    public void removerAtor(Ator a) {
        lstAtores.remove(a);
    }

    /**
     * Pesquisa um ator pelo nome exato.
     *
     * @param nome nome do ator
     * @return ator encontrado ou null se não existir
     */
    public Ator pesquisaAtor(String nome) {
        for (Ator a : lstAtores) {
            if (a.temNome(nome)) return a;
        }
        return null;
    }

    /**
     * Pesquisa atores cujo nome contenha o texto indicado.
     *
     * @param texto texto a pesquisar
     * @return lista de atores que correspondem
     */
    public ArrayList<Ator> pesquisarAtores(String texto) {
        ArrayList<Ator> resultado = new ArrayList<>();
        for (Ator a : lstAtores) {
            if (a.correspondePesquisa(texto)) resultado.add(a);
        }
        return resultado;
    }

    /**
     * Devolve a lista de todos os atores.
     *
     * @return lista de atores
     */
    public ArrayList<Ator> getLstAtores() {
        return lstAtores;
    }

    // ===== RECURSOS =====

    /**
     * Adiciona um recurso à plataforma.
     *
     * @param recurso recurso a adicionar
     * @throws Exception se já existir um recurso com o mesmo título e ano
     */
    public void adicionarRecurso(Recurso recurso) throws Exception {
        for (Recurso existente : lstRecursos) {
            if (existente.isDuplicado(recurso.getTitulo(), recurso.getAno())) {
                throw new Exception("Já existe um recurso com o título '"
                        + recurso.getTitulo() + "' e ano " + recurso.getAno() + ".");
            }
        }
        lstRecursos.add(recurso);
    }

    /**
     * Remove um recurso da plataforma.
     *
     * @param recurso recurso a remover
     */
    public void removerRecurso(Recurso recurso) {
        lstRecursos.remove(recurso);
    }

    /**
     * Pesquisa recursos cujo título contenha o texto indicado.
     *
     * @param texto texto a pesquisar
     * @return lista de recursos que correspondem
     */
    public ArrayList<Recurso> pesquisarRecursos(String texto) {
        ArrayList<Recurso> resultado = new ArrayList<>();
        for (Recurso recurso : lstRecursos) {
            if (recurso.correspondePesquisa(texto)) resultado.add(recurso);
        }
        return resultado;
    }

    /**
     * Devolve todos os filmes da plataforma.
     *
     * @return lista de filmes
     */
    public ArrayList<Filme> getFilmes() {
        ArrayList<Filme> filmes = new ArrayList<>();
        for (Recurso r : lstRecursos) {
            if (r instanceof Filme) filmes.add((Filme) r);
        }
        return filmes;
    }

    /**
     * Devolve todas as séries da plataforma.
     *
     * @return lista de séries
     */
    public ArrayList<Serie> getSeries() {
        ArrayList<Serie> series = new ArrayList<>();
        for (Recurso r : lstRecursos) {
            if (r instanceof Serie) series.add((Serie) r);
        }
        return series;
    }

    /**
     * Devolve a lista de todos os recursos.
     *
     * @return lista de recursos
     */
    public ArrayList<Recurso> getLstRecursos() {
        return lstRecursos;
    }

    // ===== CLASSIFICAÇÕES =====

    /**
     * Classifica um filme por um espectador.
     *
     * @param filme      filme a classificar
     * @param espectador espectador que classifica
     * @param valor      valor entre 1 espectador 10
     * @throws Exception se o valor for inválido, já classificou ou não viu
     */
    public void classificarFilme(Filme filme, Espectador espectador, int valor) throws Exception {
        if (valor < 1 || valor > 10)
            throw new Exception("A classificação deve estar entre 1 espectador 10.");
        if (!filme.isVisto(espectador))
            throw new Exception("Só pode classificar um filme que já viu.");
        if (filme.jaClassificou(espectador))
            throw new Exception("Já classificou este filme.");
        filme.adicionarClassificacao(new Classificacao(valor, espectador));
    }

    /**
     * Classifica um episódio por um espectador.
     *
     * @param episodio   episódio a classificar
     * @param espectador espectador que classifica
     * @param valor      valor entre 1 e 10
     * @throws Exception se o valor for inválido, já classificou ou não viu
     */
    public void classificarEpisodio(Episodio episodio, Espectador espectador, int valor) throws Exception {
        if (valor < 1 || valor > 10)
            throw new Exception("A classificação deve estar entre 1 e 10.");
        if (!episodio.isVisto(espectador))
            throw new Exception("Só pode classificar um episódio que já viu.");
        if (episodio.jaClassificou(espectador))
            throw new Exception("Já classificou este episódio.");
        episodio.adicionarClassificacao(new Classificacao(valor, espectador));
    }

    // ===== ORDENAÇÕES =====

    /**
     * Devolve a lista de filmes ordenada por título (A-Z).
     *
     * @return lista de filmes ordenada por título
     */
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

    /**
     * Devolve a lista de filmes ordenada por classificação média (decrescente).
     *
     * @return lista de filmes ordenada por classificação
     */
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

    /**
     * Devolve a lista de atores ordenada por nome (A-Z).
     *
     * @return lista de atores ordenada por nome
     */
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

    /**
     * Devolve a lista de atores ordenada por número de participações (decrescente).
     *
     * @return lista de atores ordenada por participações
     */
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

    /**
     * Conta o número de participações de um ator em filmes e episódios.
     *
     * @param ator ator a contar
     * @return número de participações
     */
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

    /**
     * Devolve a lista de espectadores ordenada por filmes vistos (decrescente).
     *
     * @return lista de espectadores ordenada
     */
    public ArrayList<Espectador> listarEspectadoresPorFilmesVistos() {
        ArrayList<Espectador> espectadores = new ArrayList<>();
        for (UtilizadorRegistado utilizadorRegistado : lstUtilizadores) {
            if (utilizadorRegistado instanceof Espectador) espectadores.add((Espectador) utilizadorRegistado);
        }
        Collections.sort(espectadores, new Comparator<Espectador>() {
            @Override
            public int compare(Espectador e1, Espectador e2) {
                return Integer.compare(contarFilmesVistos(e2), contarFilmesVistos(e1));
            }
        });
        return espectadores;
    }

    /**
     * Conta o número de filmes vistos por um espectador.
     *
     * @param espectador espectador a contar
     * @return número de filmes vistos
     */
    private int contarFilmesVistos(Espectador espectador) {
        int count = 0;
        for (Filme f : getFilmes()) {
            if (f.isVisto(espectador)) count++;
        }
        return count;
    }

    // ===== LISTAGENS =====

    /**
     * Devolve a lista de utilizadores formatada.
     *
     * @return string com todos os utilizadores
     */
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

    /**
     * Devolve a lista de atores formatada.
     *
     * @return string com todos os atores
     */
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

    /**
     * Devolve a lista de recursos formatada.
     *
     * @return string com todos os recursos
     */
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

    /**
     * Devolve o estado completo da plataforma.
     *
     * @return string com utilizadores, atores e recursos
     */
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