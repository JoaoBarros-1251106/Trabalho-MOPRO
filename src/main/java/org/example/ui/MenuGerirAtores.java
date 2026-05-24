package org.example.ui;

import org.example.model.Ator;
import org.example.model.DB;
import org.example.utils.Utils;

import java.util.ArrayList;

public class MenuGerirAtores {

    private DB imdb;
    private String opcao;

    public MenuGerirAtores(DB imdb) {
        this.imdb = imdb;
    }

    public void run() {
        do {
            System.out.println("\n\n");
            System.out.println("#################################################");
            System.out.println("#              GERIR ATORES                     #");
            System.out.println("#################################################");
            System.out.println("#                                               #");
            System.out.println("#  1. Ver atores                                #");
            System.out.println("#  2. Adicionar ator                            #");
            System.out.println("#  3. Remover ator                              #");
            System.out.println("#  4. Atores ordenados por nome                 #");
            System.out.println("#  5. Atores ordenados por nº de filmes         #");
            System.out.println("#                                               #");
            System.out.println("#  0. Voltar                                    #");
            System.out.println("#                                               #");
            System.out.println("#################################################");
            System.out.println();

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            switch (opcao) {
                case "1":
                    System.out.println(imdb.listarAtores());
                    break;
                case "2":
                    new RegistarAtor(imdb).run();
                    break;
                case "3":
                    new RemoverAtor(imdb).run();
                    break;
                case "4":
                    listarAtoresPorNome();
                    break;
                case "5":
                    listarAtoresPorNumFilmes();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (!opcao.equals("0"));
    }

    private void listarAtoresPorNome() {
        ArrayList<Ator> atores = imdb.listarAtoresPorNome();
        System.out.println("\n--- Atores por nome ---");
        for (Ator a : atores) {
            System.out.println(" - " + a);
        }
    }

    private void listarAtoresPorNumFilmes() {
        ArrayList<Ator> atores = imdb.listarAtoresPorNumFilmes();
        System.out.println("\n--- Atores por nº de participações ---");
        for (Ator a : atores) {
            System.out.println(" - " + a);
        }
    }
}