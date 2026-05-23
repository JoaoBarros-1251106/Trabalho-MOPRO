package org.example.ui;

import org.example.model.*;
import org.example.utils.Data;
import org.example.utils.Utils;
import org.example.ui.GestorFicheiros;

public class MenuFonteInfo {
    private DB imdb;
    private String opcao;

    public MenuFonteInfo(DB imdb) {
        this.imdb = imdb;
    }

    public void run() {
        do {
            System.out.println("\n\n");
            System.out.println("#################################################");
            System.out.println("#              FONTE DA INFORMAÇÃO              #");
            System.out.println("#################################################");
            System.out.println("#                                               #");
            System.out.println("#  1. Carregar dados demo                       #");
            System.out.println("#  2. Carregar de ficheiro                      #");
            System.out.println("#                                               #");
            System.out.println("#  0. Sair                                      #");
            System.out.println("#                                               #");
            System.out.println("#################################################");
            System.out.println();

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");
            switch (opcao) {
                case "1":
                    imdb = construir();
                    System.out.println("Carregada DB com dados demo");
                    System.out.println("----------------------------");
                    System.out.println("| CREDENCIAIS DEMO:        |");
                    System.out.println("----------------------------");
                    System.out.println("| -> Admin:                |");
                    System.out.println("|    - admin/admin         |");
                    System.out.println("| -> Espectadores:         |");
                    System.out.println("|    - ana/abc             |");
                    System.out.println("|    - pedro/qwerty        |");
                    System.out.println("----------------------------");
                    break;
                case "2":
                    // Carrega a base de dados a partir do ficheiro
                    imdb = GestorFicheiros.carregar();
                    break;

                case "0":
                    // Guarda os dados no ficheiro antes de terminar a aplicação
                    if (imdb != null) {
                        System.out.println("A guardar os dados...");
                        GestorFicheiros.guardar(imdb);
                    }
                    System.out.println("Aplicação terminada.");
                    break;
            }

            // Só avança para o MenuInicial se a DB não for nula E a opção não for Sair (0)
            if (imdb != null && !opcao.equals("0")) {
                System.out.println(imdb);
                MenuInicial uiMenu = new MenuInicial(imdb);
                uiMenu.run();
            }

        } while (!opcao.equals("0"));
    }

    private static DB construir() {
        // Construção da empresa
        DB imdb = new DB("www.imdb.com");

        // Utilizadores
        Admin admin = criarAdmin(imdb, "admin@example.com", "admin", "admin");
        Espectador ana = criarEspectador("ana@example.com", "ana", "abc", imdb);
        Espectador pedro = criarEspectador("pedro@example.com", "pedro", "qwerty", imdb);

        // Atores
        Ator pierceBrosnan = criarAtor(imdb, "Pierce Brosnan", new Data(1953, 5, 16));
        Ator tomHardy = criarAtor(imdb, "Tom Hardy", new Data(1977, 9, 15));
        Ator helenMirren = criarAtor(imdb, "Helen Mirren", new Data(1945, 7, 26));
        Ator jonathanPrice = criarAtor(imdb, "Jonathan Price", new Data(1947, 6, 1));
        Ator cillianMurphy = criarAtor(imdb, "Cillian Murphy", new Data(1976, 5, 25));
        Ator BrianCranston = criarAtor(imdb, "Brian Cranston", new Data(1956, 3, 7));

        // Filmes
        Filme inception = criarFilme(imdb, "Inception", 2010, 148, Genero.SCI_FI, tomHardy);
        Filme goldeneye = criarFilme(imdb, "GoldenEye", 1995, 130, Genero.ACTION, pierceBrosnan);
        Filme darkKnight = criarFilme(imdb, "The Dark Knight", 2008, 152, Genero.ACTION, tomHardy);
        Filme theQueen = criarFilme(imdb, "The Queen", 2006, 103, Genero.DRAMA, helenMirren);


        // Series
        Serie BreakingBad = criarSerie(imdb, "Breaking Bad", 2008, Genero.CRIME);
        if (BreakingBad != null) {
            Temporada t1 = new Temporada(1, 2008);
            BreakingBad.adicionarTemporada(t1);
            Episodio ep1 = new Episodio("Pilot", 1, 58, BrianCranston);
            Episodio ep2 = new Episodio("Cat's in the Bag...", 2, 48, BrianCranston);
            Episodio ep3 = new Episodio("...And the Bag's in the River", 3, 48, BrianCranston);
            Episodio ep4 = new Episodio("Cancer Man", 4, 48, BrianCranston);
            Episodio ep5 = new Episodio("Gray Matter", 5, 48, BrianCranston);
            Episodio ep6 = new Episodio("Crazy Handful of Nothin'", 6, 48, BrianCranston);
            Episodio ep7 = new Episodio("A No-Rough-Stuff-Type Deal", 7, 47, BrianCranston);
            t1.adicionarEpisodio(ep1);
            t1.adicionarEpisodio(ep2);
            t1.adicionarEpisodio(ep3);
            t1.adicionarEpisodio(ep4);
            t1.adicionarEpisodio(ep5);
            t1.adicionarEpisodio(ep6);
            t1.adicionarEpisodio(ep7);

        }

        try {
            if (inception != null) {
                inception.marcarComoVisto(ana);
                ana.classificarFilme(inception, 9);
                ana.comentarFilme(inception, "Obra-prima do cinema!");

                inception.marcarComoVisto(pedro);
                pedro.classificarFilme(inception, 8);
            }
            if (darkKnight != null) {
                darkKnight.marcarComoVisto(ana);
                ana.classificarFilme(darkKnight, 10);
            }
            if (goldeneye != null) {
                goldeneye.marcarComoVisto(pedro);
                pedro.classificarFilme(goldeneye, 7);
                pedro.comentarFilme(goldeneye, "Clássico do Bond!");
            }
        } catch (Exception e) {
            System.out.println("Erro nos dados demo: " + e.getMessage());
        }
        return imdb;


    }



    private static Ator criarAtor(DB imdb, String nome, Data dataNascimento) {
        Ator ator = new Ator(nome, dataNascimento);
        imdb.adicionarAtor(ator);
        System.out.println("Ator '" + nome + "' criado com sucesso");
        return ator;
    }

    private static Espectador criarEspectador(String email, String nome, String password, DB imdb) {
        Espectador espectador = new Espectador(email, nome, password);
        imdb.adicionarUtilizador(espectador);
        System.out.println("Espectador '" + nome + "' criado com sucesso");
        return espectador;
    }

    private static Admin criarAdmin(DB imdb, String email, String nome, String password) {
        Admin admin = new Admin(email, nome, password);
        imdb.adicionarUtilizador(admin);
        System.out.println("Administrador '" + nome + "' criado com sucesso");
        return admin;
    }

    private static Filme criarFilme(DB imdb, String titulo, int ano, int dur, Genero genero, Ator ator){
        Filme Filme = new Filme(titulo, ano, dur, genero, ator);
        imdb.adicionarFilme(Filme);
        System.out.println("Administrador '" + Filme + "' criado com sucesso");
        return Filme;

    }
    private static Serie criarSerie(DB imdb, String titulo, int ano, Genero genero){
        Serie Serie = new Serie(titulo, ano, genero);
        imdb.adicionarSerie(Serie);
        System.out.println("Administrador '" + Serie + "' criado com sucesso");
        return Serie;

    }
}