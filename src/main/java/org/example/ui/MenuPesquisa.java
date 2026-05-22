package org.example.ui;

import org.example.model.Ator;
import org.example.model.DB;
import org.example.model.Filme;
import org.example.model.Serie;
import org.example.utils.Utils;

public class MenuPesquisa {
    private DB imdb;
    private String opcao;

    public MenuPesquisa(DB imdb) {
        this.imdb = imdb;
    }

    public void run() {
        do {
            System.out.println("\n\n");
            System.out.println("#################################################");
            System.out.println("#                 MENU PESQUISA                 #");
            System.out.println("#################################################");
            System.out.println("#                                               #");
            System.out.println("#  1. Pesquisar filmes                          #");
            System.out.println("#  2. Pesquisar séries                          #");
            System.out.println("#  3. Pesquisar atores                          #");
            System.out.println("#                                               #");
            System.out.println("#  0. Voltar                                    #");
            System.out.println("#                                               #");
            System.out.println("#################################################");
            System.out.println();

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            switch (opcao) {
                case "1":
                    pesquisarFilmes();
                    break;
                case "2":
                    pesquisarSeries();
                    break;
                case "3":
                    pesquisarAtores();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (!opcao.equals("0"));
    }

    // ==========================================
    // 1. PESQUISAR FILMES
    // ==========================================
    private void pesquisarFilmes() {
        System.out.println("\n--- Pesquisar Filmes ---");
        String texto = Utils.readLineFromConsole("Introduza o texto a pesquisar (título): ");
        boolean encontrou = false;

        for (int i = 0; i < imdb.getFilmes().size(); i++) {
            Filme f = imdb.getFilmes().get(i);
            if (f.correspondePesquisa(texto)) {
                System.out.println(" - " + f);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum filme encontrado com esse texto.");
        }
    }

    // ==========================================
    // 2. PESQUISAR SÉRIES
    // ==========================================
    private void pesquisarSeries() {
        System.out.println("\n--- Pesquisar Séries ---");
        String texto = Utils.readLineFromConsole("Introduza o texto a pesquisar (título): ");
        boolean encontrou = false;

        for (int i = 0; i < imdb.getSeries().size(); i++) {
            Serie s = imdb.getSeries().get(i);
            if (s.correspondePesquisa(texto)) {
                System.out.println(" - " + s);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma série encontrada com esse texto.");
        }
    }

    // ==========================================
    // 3. PESQUISAR ATORES
    // ==========================================
    private void pesquisarAtores() {
        System.out.println("\n--- Pesquisar Atores ---");
        String texto = Utils.readLineFromConsole("Introduza o texto a pesquisar (nome): ");
        boolean encontrou = false;

        for (int i = 0; i < imdb.getAtores().size(); i++) {
            Ator a = imdb.getAtores().get(i);
            // Verifica se o nome do ator contém o texto pesquisado (ignorando maiúsculas/minúsculas)
            if (a.getNome().toLowerCase().contains(texto.toLowerCase())) {
                System.out.println(" - " + a);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum ator encontrado com esse nome.");
        }
    }
}