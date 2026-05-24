package org.example.ui;

import org.example.model.DB;
import org.example.model.Filme;
import org.example.model.Recurso;
import org.example.model.Serie;
import org.example.utils.Utils;

import java.util.ArrayList;

/**
 * Classe que representa a interface para visitantes ou utilizadores não autenticados.
 * Fornece funcionalidades limitadas apenas de leitura e pesquisa, como listar e
 * consultar os recursos (filmes, séries) e os atores presentes na base de dados.
 */

public class MenuSemLogin {

    private DB imdb;
    private String opcao;

    public MenuSemLogin(DB imdb) {
        this.imdb = imdb;
    }

    public void run() {
        do {
            System.out.println("\n\n");
            System.out.println("#################################################");
            System.out.println("#              MENU - SEM LOGIN                 #");
            System.out.println("#################################################");
            System.out.println("#                                               #");
            System.out.println("#  1. Listar atores                             #");
            System.out.println("#  2. Listar filmes                             #");
            System.out.println("#  3. Listar séries                             #");
            System.out.println("#  4. Pesquisar filme/série por título          #");
            System.out.println("#  5. Pesquisar ator por nome                   #");
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
                    listarFilmes();
                    break;
                case "3":
                    listarSeries();
                    break;
                case "4":
                    pesquisarRecursos();
                    break;
                case "5":
                    pesquisarAtores();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (!opcao.equals("0"));
    }

    private void listarFilmes() {
        ArrayList<Filme> filmes = imdb.getFilmes();
        if (filmes.isEmpty()) {
            System.out.println("Não existem filmes registados.");
            return;
        }
        System.out.println("\n--- Lista de Filmes ---");
        for (Filme f : filmes) {
            System.out.println(" - " + f);
        }
    }

    private void listarSeries() {
        ArrayList<Serie> series = imdb.getSeries();
        if (series.isEmpty()) {
            System.out.println("Não existem séries registadas.");
            return;
        }
        System.out.println("\n--- Lista de Séries ---");
        for (Serie s : series) {
            System.out.println(" - " + s.getTitulo() + " (" + s.getAno() + ")");
        }
    }

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

    private void pesquisarAtores() {
        String texto = Utils.readLineFromConsole("Pesquisar ator (nome): ");
        ArrayList<org.example.model.Ator> resultado = imdb.pesquisarAtores(texto);
        if (resultado.isEmpty()) {
            System.out.println("Nenhum ator encontrado.");
        } else {
            for (org.example.model.Ator a : resultado) {
                System.out.println(" - " + a);
            }
        }
    }
}