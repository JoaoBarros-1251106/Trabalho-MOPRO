package org.example.ui;

import org.example.model.DB;
import org.example.utils.Utils;

public class MenuAdministrador {

    private DB imdb;
    private String opcao;

    public MenuAdministrador(DB imdb) {
        this.imdb = imdb;
    }

    public void run() {
        do {
            System.out.println("\n\n");
            System.out.println("#################################################");
            System.out.println("#            MENU - ADMINISTRADOR               #");
            System.out.println("#################################################");
            System.out.println("#                                               #");
            System.out.println("#  1. Gerir atores                              #");
            System.out.println("#  2. Gerir recursos (filmes/séries)            #");
            System.out.println("#  3. Ver utilizadores                          #");
            System.out.println("#  4. Ver estado da plataforma                  #");
            System.out.println("#                                               #");
            System.out.println("#  0. Voltar                                    #");
            System.out.println("#                                               #");
            System.out.println("#################################################");
            System.out.println();

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            switch (opcao) {
                case "1":
                    new MenuGerirAtores(imdb).run();
                    break;
                case "2":
                    new MenuGerirRecursos(imdb).run();
                    break;
                case "3":
                    System.out.println(imdb.listarUtilizadores());
                    break;
                case "4":
                    System.out.println(imdb);
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (!opcao.equals("0"));
    }
}