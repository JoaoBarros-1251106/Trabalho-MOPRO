package org.example.ui;

import org.example.model.DB;
import org.example.model.Espectador;
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
            System.out.println("#                     MENU                      #");
            System.out.println("#################################################");
            System.out.println("#                                               #");
            System.out.println("#  1. Gerir atores                              #");
            System.out.println("#  2. Gerir filmes                              #");
            System.out.println("#  3. Gerir séries                              #");
            System.out.println("#  4. Pesquisa                                  #");
            System.out.println("#  5. Ver utilizadores                          #");
            System.out.println("#  6. Ver estado da plataforma                  #");
            System.out.println("#                                               #");
            System.out.println("#  0. Voltar                                    #");
            System.out.println("#                                               #");
            System.out.println("#################################################");
            System.out.println();
            //adicionado
            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            switch (opcao) {

                case "1":
                    new MenuGerirAtores(imdb).run();
                    break;

                case "2":
                    new MenuGerirFilmes(imdb).run();
                    break;

                case "3":
                    new MenuGerirSeries(imdb).run();
                    break;

                case "4":
                    new MenuPesquisa(imdb).run();
                    break;

                case "5":
                    System.out.println(imdb.listarUtilizadores());
                    break;

                case "6":
                    System.out.println(imdb);
                    break;

                case "0":
                    System.out.println("A sair...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }
        while (!opcao.equals("0"));
    }
    private void registarEspectador() {
        System.out.println("\n --- Registar Espectador ---");
        String username = Utils.readLineFromConsole("Username: ");
        String email    = Utils.readLineFromConsole("Email: ");
        String password = Utils.readLineFromConsole("Password: ");
        imdb.adicionarUtilizador(new Espectador(username, email, password));
        System.out.println("Espectador '" + username + "' registado com sucesso!");
    }
}
