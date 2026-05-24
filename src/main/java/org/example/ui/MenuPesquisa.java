package org.example.ui;

import org.example.model.Ator;
import org.example.model.DB;
import org.example.model.Filme;
import org.example.model.Recurso;
import org.example.model.Serie;
import org.example.utils.Utils;

import java.util.ArrayList;

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
            System.out.println("#  1. Pesquisar filmes/séries por título        #");
            System.out.println("#  2. Pesquisar atores por nome                 #");
            System.out.println("#                                               #");
            System.out.println("#  0. Voltar                                    #");
            System.out.println("#                                               #");
            System.out.println("#################################################");
            System.out.println();

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            switch (opcao) {
                case "1":
                    pesquisarRecursos();
                    break;
                case "2":
                    pesquisarAtores();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (!opcao.equals("0"));
    }

    // Pesquisa filmes e séries pelo título
    private void pesquisarRecursos() {
        String texto = Utils.readLineFromConsole("Pesquisar (título): ");
        ArrayList<Recurso> resultado = imdb.pesquisarRecursos(texto);
        if (resultado.isEmpty()) {
            System.out.println("Nenhum resultado encontrado.");
        } else {
            for (Recurso r : resultado) {
                System.out.println(" - " + r.getTitulo() + " (" + r.getAno() + ")"
                        + " [" + (r instanceof Filme ? "Filme" : "Série") + "]");
            }
        }
    }

    // Pesquisa atores pelo nome
    private void pesquisarAtores() {
        String texto = Utils.readLineFromConsole("Pesquisar ator (nome): ");
        ArrayList<Ator> resultado = imdb.pesquisarAtores(texto);
        if (resultado.isEmpty()) {
            System.out.println("Nenhum ator encontrado.");
        } else {
            for (Ator a : resultado) {
                System.out.println(" - " + a);
            }
        }
    }
}