package org.example.ui;

import org.example.model.DB;
import org.example.model.Espectador;
import org.example.utils.Utils;

import java.util.ArrayList;

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
            System.out.println("#  4. Adicionar espectador                      #");
            System.out.println("#  5. Utilizadores com mais filmes vistos       #");
            System.out.println("#  6. Ver estado da plataforma                  #");
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
                    adicionarEspectador();
                    break;
                case "5":
                    listarEspectadoresPorFilmesVistos();
                    break;
                case "6":
                    System.out.println(imdb);
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (!opcao.equals("0"));
    }

    // Adiciona um novo espectador à plataforma
    private void adicionarEspectador() {
        String email = Utils.readLineFromConsole("Email: ");
        String nome = Utils.readLineFromConsole("Username: ");
        String password = Utils.readLineFromConsole("Password: ");
        imdb.adicionarUtilizador(new Espectador(email, nome, password));
        System.out.println("Espectador '" + nome + "' adicionado com sucesso.");
        System.out.println(imdb.listarUtilizadores());
    }

    // Lista espectadores ordenados por número de filmes vistos
    private void listarEspectadoresPorFilmesVistos() {
        ArrayList<Espectador> espectadores = imdb.listarEspectadoresPorFilmesVistos();
        System.out.println("\n--- Espectadores por filmes vistos ---");
        for (Espectador e : espectadores) {
            System.out.println(" - " + e.getNome());
        }
    }
}