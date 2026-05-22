package org.example.ui;

import org.example.model.Ator;
import org.example.model.DB;
import org.example.utils.Utils;

import java.util.ArrayList;
import java.util.Comparator;

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
            System.out.println("#                     MENU                      #");
            System.out.println("#################################################");
            System.out.println("#                                               #");
            System.out.println("#  1. Ver atores                                #");
            System.out.println("#  2. Adicionar ator                            #");
            System.out.println("#  3. Remover ator                              #");
            System.out.println("#  4. Ordenar atores por nome                   #");
            System.out.println("#  5. Ordenar atores por nº filmes              #");
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
                    RegistarAtor reg = new RegistarAtor(imdb);
                    reg.run();
                    break;
                case "3":
                    RemoverAtor remover = new RemoverAtor(imdb);
                    remover.run();
                    break;
                case "4":
                    ordenarPorNome();
                    break;
                case "5":
                    ordenarPorNumeroFilmes();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while (!opcao.equals("0"));
    }

    private void ordenarPorNome() {
        System.out.println("\n--- Atores Ordenados por Nome ---");
        ArrayList<Ator> atores = imdb.getAtores();

        if (atores.isEmpty()) {
            System.out.println("Não existem atores registados.");
            return;
        }

        // Ordenação por nome pedida no Passo 11
        atores.sort(Comparator.comparing(Ator::getNome));

        for (Ator a : atores) {
            System.out.println(" - " + a.getNome());
        }
    }

    private void ordenarPorNumeroFilmes() {
        System.out.println("\n--- Atores Ordenados por Nº de Participações ---");
        ArrayList<Ator> atores = imdb.getAtores();

        if (atores.isEmpty()) {
            System.out.println("Não existem atores registados.");
            return;
        }

        // Ordenação decrescente (do maior número de participações para o menor)
        atores.sort((a1, a2) -> Integer.compare(a2.getNumeroParticipacoes(), a1.getNumeroParticipacoes()));

        for (Ator a : atores) {
            System.out.println(" - " + a.getNome() + " | Participações: " + a.getNumeroParticipacoes());
        }
    }
}