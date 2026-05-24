package org.example.ui;

import org.example.model.DB;
import org.example.utils.Utils;

public class MenuGerirRecursos {

    private DB imdb;
    private String opcao;

    public MenuGerirRecursos(DB imdb) {
        this.imdb = imdb;
    }

    public void run() {
        do {
            System.out.println("\n\n");
            System.out.println("#################################################");
            System.out.println("#           GERIR RECURSOS                      #");
            System.out.println("#################################################");
            System.out.println("#                                               #");
            System.out.println("#  1. Gerir filmes                              #");
            System.out.println("#  2. Gerir séries                              #");
            System.out.println("#                                               #");
            System.out.println("#  0. Voltar                                    #");
            System.out.println("#                                               #");
            System.out.println("#################################################");
            System.out.println();

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            switch (opcao) {
                case "1":
                    new MenuGerirFilmes(imdb).run();
                    break;
                case "2":
                    new MenuGerirSeries(imdb).run();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (!opcao.equals("0"));
    }
}