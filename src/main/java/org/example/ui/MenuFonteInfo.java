package org.example.ui;

import org.example.model.*;
import org.example.persistence.Persistencia;
import org.example.utils.Data;
import org.example.utils.Utils;

/**
 * Classe responsável pelo menu inicial de seleção da fonte de dados da plataforma.
 * Permite carregar dados de demonstração construídos em código, ler o estado guardado
 * a partir de um ficheiro ou gravar o estado atual num ficheiro.
 */

public class MenuFonteInfo {

    private DB imdb;
    private String opcao;

    public MenuFonteInfo(DB imdb) {
        this.imdb = imdb;
    }

    /**
     * Inicia o ciclo de execução do menu de escolha da fonte de informação.
     */


    public void run() {
        do {
            System.out.println("\n\n");
            System.out.println("#################################################");
            System.out.println("#              FONTE DA INFORMAÇÃO              #");
            System.out.println("#################################################");
            System.out.println("#                                               #");
            System.out.println("#  1. Carregar dados demo                       #");
            System.out.println("#  2. Carregar de ficheiro                      #");
            System.out.println("#  3. Gravar dados em ficheiro                  #");
            System.out.println("#                                               #");
            System.out.println("#  0. Sair                                      #");
            System.out.println("#                                               #");
            System.out.println("#################################################");
            System.out.println();

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            switch (opcao) {
                case "1":
                    imdb = construir();
                    System.out.println("Carregada DB com dados demo.");
                    System.out.println("----------------------------");
                    System.out.println("| CREDENCIAIS DEMO:        |");
                    System.out.println("| -> Admin:  admin/admin   |");
                    System.out.println("| -> ana/abc               |");
                    System.out.println("| -> pedro/qwerty          |");
                    System.out.println("----------------------------");
                    break;
                case "2":
                    DB carregada = Persistencia.lerDados();
                    if (carregada != null) {
                        imdb = carregada;
                        System.out.println("Dados carregados com sucesso.");
                    } else {
                        System.out.println("Não foi possível carregar dados.");
                    }
                    break;
                case "3":
                    if (imdb == null) {
                        System.out.println("Não há dados para gravar.");
                    } else {
                        boolean ok = Persistencia.gravarDados(imdb);
                        System.out.println(ok ? "Dados gravados com sucesso." : "Erro ao gravar.");
                    }
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

            if (imdb != null && !opcao.equals("0") && !opcao.equals("3")) {
                System.out.println(imdb);
                new MenuInicial(imdb).run();
            }

        } while (!opcao.equals("0"));
    }

    /**
     * Constrói e retorna uma base de dados (DB) com dados de demonstração predefinidos.
     * Insere utilizadores (administrador e espectadores), atores, filmes e séries para testes.
     *
     * @return Uma instância preenchida da base de dados (DB).
     */


    private static DB construir() {
        DB imdb = new DB("www.imdb.com");

        /**
         Utilizadores
         */
        Admin admin = new Admin("admin@example.com", "admin", "admin");
        imdb.adicionarUtilizador(admin);

        Espectador ana = new Espectador("ana@example.com", "ana", "abc");
        imdb.adicionarUtilizador(ana);

        Espectador pedro = new Espectador("pedro@example.com", "pedro", "qwerty");
        imdb.adicionarUtilizador(pedro);


        /**
         Atores
         */

        Ator tomHardy = new Ator("Tom Hardy", new Data(1977, 9, 15));
        imdb.adicionarAtor(tomHardy);

        Ator pierceBrosnan = new Ator("Pierce Brosnan", new Data(1953, 5, 16));
        imdb.adicionarAtor(pierceBrosnan);

        Ator cillianMurphy = new Ator("Cillian Murphy", new Data(1976, 5, 25));
        imdb.adicionarAtor(cillianMurphy);

        Ator brianCranston = new Ator("Brian Cranston", new Data(1956, 3, 7));
        imdb.adicionarAtor(brianCranston);

        /**
         Filmes
         */

        try {
            Filme inception = new Filme("Inception", 2010, 148);
            inception.adicionarGenero(Genero.SCI_FI);
            inception.adicionarAtor(tomHardy);
            imdb.adicionarRecurso(inception);

            inception.marcarComoVisto(ana);
            imdb.classificarFilme(inception, ana, 9);
            inception.adicionarComentario(new Comentario(ana, "Obra-prima!"));

            inception.marcarComoVisto(pedro);
            imdb.classificarFilme(inception, pedro, 8);

            Filme goldeneye = new Filme("GoldenEye", 1995, 130);
            goldeneye.adicionarGenero(Genero.ACTION);
            goldeneye.adicionarAtor(pierceBrosnan);
            imdb.adicionarRecurso(goldeneye);

            goldeneye.marcarComoVisto(pedro);
            imdb.classificarFilme(goldeneye, pedro, 7);

            /**
             Series
             */
            Serie breakingBad = new Serie("Breaking Bad", 2008);
            breakingBad.adicionarGenero(Genero.CRIME);
            breakingBad.adicionarAtor(brianCranston);
            imdb.adicionarRecurso(breakingBad);

            Temporada t1 = new Temporada(1, 2008);
            Episodio ep1 = new Episodio("Pilot", 1, 58);
            ep1.adicionarAtor(brianCranston);
            Episodio ep2 = new Episodio("Cat's in the Bag", 2, 48);
            ep2.adicionarAtor(brianCranston);
            t1.adicionarEpisodio(ep1);
            t1.adicionarEpisodio(ep2);
            breakingBad.adicionarTemporada(t1);

            ep1.marcarComoVisto(pedro);
            imdb.classificarEpisodio(ep1, pedro, 9);

        } catch (Exception e) {
            System.out.println("Erro nos dados demo: " + e.getMessage());
        }

        return imdb;
    }
}