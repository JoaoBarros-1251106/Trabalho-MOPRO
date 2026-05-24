package org.example.ui;

import org.example.model.DB;
import org.example.utils.Utils;

/**
 * Submenu direcionado à escolha do tipo de recurso a gerir pelo administrador.
 * Funciona como intermediário para reencaminhar o utilizador para a gestão
 * específica de filmes ou de séries.
 */


public class MenuGerirRecursos {

    private DB imdb;
    private String opcao;

    /**
     * Construtor da classe MenuGerirRecursos.
     *
     * @param imdb A base de dados principal que detém os recursos do sistema.
     */

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