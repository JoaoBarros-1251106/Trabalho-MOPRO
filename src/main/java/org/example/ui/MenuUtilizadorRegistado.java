package org.example.ui;

import org.example.model.*;
import org.example.utils.Utils;

import java.util.ArrayList;

/**
 * Classe que fornece e processa o menu específico para o utilizador com privilégios de Espectador.
 * Permite uma forte interação com a plataforma, através de marcações de visualizações,
 * avaliações (classificações), escrita de comentários e administração de uma lista pessoal.
 */
public class MenuUtilizadorRegistado {

    private DB imdb;
    private Espectador utilizador;
    private String opcao;

    /**
     * Construtor da classe MenuUtilizadorRegistado.
     *
     * @param imdb A base de dados que contém toda a informação da plataforma.
     * @param utilizador A instância de Espectador que está atualmente autenticada.
     */

    public MenuUtilizadorRegistado(DB imdb, Espectador utilizador) {
        this.imdb = imdb;
        this.utilizador = utilizador;
    }

    public void run() {
        do {
            System.out.println("\n\n");
            System.out.println("#################################################");
            System.out.println("#         MENU - " + utilizador.getNome());
            System.out.println("#################################################");
            System.out.println("#                                               #");
            System.out.println("#  1. Pesquisar e visualizar                    #");
            System.out.println("#  2. Marcar filme como visto                   #");
            System.out.println("#  3. Marcar episódio como visto                #");
            System.out.println("#  4. Classificar filme                         #");
            System.out.println("#  5. Classificar episódio                      #");
            System.out.println("#  6. Comentar filme                            #");
            System.out.println("#  7. Comentar episódio                         #");
            System.out.println("#  8. Gerir lista pessoal                       #");
            System.out.println("#                                               #");
            System.out.println("#  0. Voltar                                    #");
            System.out.println("#                                               #");
            System.out.println("#################################################");
            System.out.println();

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            switch (opcao) {
                case "1":
                    new MenuSemLogin(imdb).run();
                    break;
                case "2":
                    marcarFilmeComoVisto();
                    break;
                case "3":
                    marcarEpisodioComoVisto();
                    break;
                case "4":
                    classificarFilme();
                    break;
                case "5":
                    classificarEpisodio();
                    break;
                case "6":
                    comentarFilme();
                    break;
                case "7":
                    comentarEpisodio();
                    break;
                case "8":
                    menuListaPessoal();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (!opcao.equals("0"));
    }

    /**
     * Apresenta a lista de filmes disponíveis e marca o filme selecionado
     * como "Visto" pelo espectador logado.
     */

    private void marcarFilmeComoVisto() {
        ArrayList<Filme> filmes = imdb.getFilmes();
        if (filmes.isEmpty()) { System.out.println("Não há filmes."); return; }
        for (int i = 0; i < filmes.size(); i++) {
            System.out.println((i + 1) + ". " + filmes.get(i).getTitulo()
                    + (filmes.get(i).isVisto(utilizador) ? " [VISTO]" : ""));
        }
        int idx = Utils.readIntFromConsole("Escolha o filme: ") - 1;
        if (idx < 0 || idx >= filmes.size()) { System.out.println("Inválido."); return; }
        try {
            filmes.get(idx).marcarComoVisto(utilizador);
            System.out.println("Filme marcado como visto!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * Utiliza a navegação hierárquica para encontrar um episódio e
     * marca-o como "Visto" pelo espetador logado.
     */

    private void marcarEpisodioComoVisto() {
        Episodio ep = escolherEpisodio();
        if (ep == null) return;
        try {
            ep.marcarComoVisto(utilizador);
            System.out.println("Episódio marcado como visto!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * Permite que o espetador atribua uma pontuação numérica a um filme específico.
     */

    private void classificarFilme() {
        ArrayList<Filme> filmes = imdb.getFilmes();
        if (filmes.isEmpty()) { System.out.println("Não há filmes."); return; }
        for (int i = 0; i < filmes.size(); i++) {
            System.out.println((i + 1) + ". " + filmes.get(i).getTitulo()
                    + (filmes.get(i).isVisto(utilizador) ? " [VISTO]" : " [NÃO VISTO]"));
        }
        int idx = Utils.readIntFromConsole("Escolha o filme: ") - 1;
        if (idx < 0 || idx >= filmes.size()) { System.out.println("Inválido."); return; }
        int valor = Utils.readIntFromConsole("Classificação (1-10): ");
        try {
            imdb.classificarFilme(filmes.get(idx), utilizador, valor);
            System.out.println("Classificação registada!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * Permite que o espetador atribua uma pontuação numérica a um episódio específico.
     */

    private void classificarEpisodio() {
        Episodio ep = escolherEpisodio();
        if (ep == null) return;
        int valor = Utils.readIntFromConsole("Classificação (1-10): ");
        try {
            imdb.classificarEpisodio(ep, utilizador, valor);
            System.out.println("Classificação registada!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * Adiciona um comentário textual feito pelo espetador logado a um filme selecionado.
     */

    private void comentarFilme() {
        ArrayList<Filme> filmes = imdb.getFilmes();
        if (filmes.isEmpty()) { System.out.println("Não há filmes."); return; }
        for (int i = 0; i < filmes.size(); i++) {
            System.out.println((i + 1) + ". " + filmes.get(i).getTitulo());
        }
        int idx = Utils.readIntFromConsole("Escolha o filme: ") - 1;
        if (idx < 0 || idx >= filmes.size()) { System.out.println("Inválido."); return; }
        String texto = Utils.readLineFromConsole("Comentário: ");
        filmes.get(idx).adicionarComentario(new Comentario(utilizador, texto));
        System.out.println("Comentário adicionado!");
    }

    /**
     * Adiciona um comentário textual feito pelo espetador logado a um episódio selecionado.
     */

    private void comentarEpisodio() {
        Episodio ep = escolherEpisodio();
        if (ep == null) return;
        String texto = Utils.readLineFromConsole("Comentário: ");
        ep.adicionarComentario(new Comentario(utilizador, texto));
        System.out.println("Comentário adicionado!");
    }

    /**
     * Abre um submenu para gestão da lista de recursos pessoais (favoritos/watchlist) do utilizador.
     */

    private void menuListaPessoal() {
        String op;
        do {
            System.out.println("\n--- Lista Pessoal ---");
            System.out.println("1. Ver lista");
            System.out.println("2. Adicionar filme");
            System.out.println("3. Remover filme");
            System.out.println("4. Adicionar episódio");
            System.out.println("5. Remover episódio");
            System.out.println("0. Voltar");
            op = Utils.readLineFromConsole("Opção: ");
            switch (op) {
                case "1":
                    System.out.println(utilizador.getListaPessoal());
                    break;
                case "2":
                    adicionarFilmeLista();
                    break;
                case "3":
                    removerFilmeLista();
                    break;
                case "4":
                    adicionarEpisodioLista();
                    break;
                case "5":
                    removerEpisodioLista();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Inválido.");
            }
        } while (!op.equals("0"));
    }

    /**
     * Adiciona um filme selecionado do sistema à lista pessoal do utilizador logado.
     */

    private void adicionarFilmeLista() {
        ArrayList<Filme> filmes = imdb.getFilmes();
        if (filmes.isEmpty()) { System.out.println("Não há filmes."); return; }
        for (int i = 0; i < filmes.size(); i++) {
            System.out.println((i + 1) + ". " + filmes.get(i).getTitulo());
        }
        int idx = Utils.readIntFromConsole("Escolha o filme: ") - 1;
        if (idx < 0 || idx >= filmes.size()) { System.out.println("Inválido."); return; }
        utilizador.getListaPessoal().adicionarFilme(filmes.get(idx));
        System.out.println("Filme adicionado à lista pessoal.");
    }

    /**
     * Remove um filme da lista pessoal do utilizador logado.
     */


    private void removerFilmeLista() {
        ArrayList<Filme> filmes = utilizador.getListaPessoal().getFilmes();
        if (filmes.isEmpty()) { System.out.println("Lista de filmes vazia."); return; }
        for (int i = 0; i < filmes.size(); i++) {
            System.out.println((i + 1) + ". " + filmes.get(i).getTitulo());
        }
        int idx = Utils.readIntFromConsole("Escolha o filme a remover: ") - 1;
        if (idx < 0 || idx >= filmes.size()) { System.out.println("Inválido."); return; }
        utilizador.getListaPessoal().removerFilme(filmes.get(idx));
        System.out.println("Filme removido da lista pessoal.");
    }

    /**
     * Adiciona um episódio escolhido pelo utilizador à sua lista pessoal.
     */

    private void adicionarEpisodioLista() {
        Episodio ep = escolherEpisodio();
        if (ep == null) return;
        utilizador.getListaPessoal().adicionarEpisodio(ep);
        System.out.println("Episódio adicionado à lista pessoal.");
    }

    /**
     * Remove um episódio da lista pessoal do utilizador logado.
     */

    private void removerEpisodioLista() {
        ArrayList<Episodio> eps = utilizador.getListaPessoal().getEpisodios();
        if (eps.isEmpty()) { System.out.println("Lista de episódios vazia."); return; }
        for (int i = 0; i < eps.size(); i++) {
            System.out.println((i + 1) + ". " + eps.get(i).getTitulo());
        }
        int idx = Utils.readIntFromConsole("Escolha o episódio a remover: ") - 1;
        if (idx < 0 || idx >= eps.size()) { System.out.println("Inválido."); return; }
        utilizador.getListaPessoal().removerEpisodio(eps.get(idx));
        System.out.println("Episódio removido da lista pessoal.");
    }

    /**
     * Método auxiliar interativo que solicita ao utilizador a escolha em cascata de uma série,
     * uma temporada e um episódio para o retornar de forma a poder ser utilizado em outras operações.
     *
     * @return O Episodio escolhido pelo utilizador ou null se a operação for cancelada ou inválida.
     */



    private Episodio escolherEpisodio() {
        ArrayList<Serie> series = imdb.getSeries();
        if (series.isEmpty()) { System.out.println("Não há séries."); return null; }
        for (int i = 0; i < series.size(); i++) {
            System.out.println((i + 1) + ". " + series.get(i).getTitulo());
        }
        int idxS = Utils.readIntFromConsole("Escolha a série: ") - 1;
        if (idxS < 0 || idxS >= series.size()) { System.out.println("Inválido."); return null; }
        ArrayList<Temporada> temps = series.get(idxS).getTemporadas();
        if (temps.isEmpty()) { System.out.println("Série sem temporadas."); return null; }
        for (int i = 0; i < temps.size(); i++) {
            System.out.println((i + 1) + ". Temporada " + temps.get(i).getNumero());
        }
        int idxT = Utils.readIntFromConsole("Escolha a temporada: ") - 1;
        if (idxT < 0 || idxT >= temps.size()) { System.out.println("Inválido."); return null; }
        ArrayList<Episodio> eps = temps.get(idxT).getEpisodios();
        if (eps.isEmpty()) { System.out.println("Temporada sem episódios."); return null; }
        for (int i = 0; i < eps.size(); i++) {
            System.out.println((i + 1) + ". " + eps.get(i).getTitulo()
                    + (eps.get(i).isVisto(utilizador) ? " [VISTO]" : ""));
        }
        int idxE = Utils.readIntFromConsole("Escolha o episódio: ") - 1;
        if (idxE < 0 || idxE >= eps.size()) { System.out.println("Inválido."); return null; }
        return eps.get(idxE);
    }
}