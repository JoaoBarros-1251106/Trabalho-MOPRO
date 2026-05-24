package org.example.ui;

import org.example.model.*;
import org.example.utils.Utils;

import java.util.ArrayList;

public class MenuGerirSeries {

    private DB imdb;
    private String opcao;

    public MenuGerirSeries(DB imdb) {
        this.imdb = imdb;
    }

    public void run() {
        do {
            System.out.println("\n\n");
            System.out.println("#################################################");
            System.out.println("#              GERIR SÉRIES                     #");
            System.out.println("#################################################");
            System.out.println("#                                               #");
            System.out.println("#  1. Listar séries                             #");
            System.out.println("#  2. Adicionar série                           #");
            System.out.println("#  3. Adicionar temporada                       #");
            System.out.println("#  4. Adicionar episódio                        #");
            System.out.println("#  5. Associar ator a episódio                  #");
            System.out.println("#  6. Adicionar género a série                  #");
            System.out.println("#                                               #");
            System.out.println("#  0. Voltar                                    #");
            System.out.println("#                                               #");
            System.out.println("#################################################");
            System.out.println();

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            switch (opcao) {
                case "1":
                    listarSeries();
                    break;
                case "2":
                    adicionarSerie();
                    break;
                case "3":
                    adicionarTemporada();
                    break;
                case "4":
                    adicionarEpisodio();
                    break;
                case "5":
                    associarAtorEpisodio();
                    break;
                case "6":
                    adicionarGeneroSerie();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (!opcao.equals("0"));
    }

    private void listarSeries() {
        ArrayList<Serie> series = imdb.getSeries();
        if (series.isEmpty()) {
            System.out.println("Não existem séries registadas.");
            return;
        }
        System.out.println("\n--- Lista de Séries ---");
        for (int i = 0; i < series.size(); i++) {
            System.out.println((i + 1) + ". " + series.get(i));
        }
    }

    private void adicionarSerie() {
        System.out.println("\n--- Adicionar Série ---");
        String titulo = Utils.readLineFromConsole("Título: ");
        int ano = Utils.readIntFromConsole("Ano: ");
        Serie novaSerie = new Serie(titulo, ano);
        try {
            imdb.adicionarRecurso(novaSerie);
            System.out.println("Série adicionada! Associa pelo menos 1 género e 1 ator.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void adicionarTemporada() {
        ArrayList<Serie> series = imdb.getSeries();
        if (series.isEmpty()) {
            System.out.println("Não existem séries registadas.");
            return;
        }
        for (int i = 0; i < series.size(); i++) {
            System.out.println((i + 1) + ". " + series.get(i).getTitulo());
        }
        int idx = Utils.readIntFromConsole("Escolha a série: ") - 1;
        if (idx < 0 || idx >= series.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        int numero = Utils.readIntFromConsole("Número da temporada: ");
        int ano = Utils.readIntFromConsole("Ano da temporada: ");
        series.get(idx).adicionarTemporada(new Temporada(numero, ano));
        System.out.println("Temporada adicionada com sucesso!");
    }

    private void adicionarEpisodio() {
        ArrayList<Serie> series = imdb.getSeries();
        if (series.isEmpty()) {
            System.out.println("Não existem séries registadas.");
            return;
        }
        for (int i = 0; i < series.size(); i++) {
            System.out.println((i + 1) + ". " + series.get(i).getTitulo());
        }
        int idxS = Utils.readIntFromConsole("Escolha a série: ") - 1;
        if (idxS < 0 || idxS >= series.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        ArrayList<Temporada> temporadas = series.get(idxS).getTemporadas();
        if (temporadas.isEmpty()) {
            System.out.println("Esta série não tem temporadas.");
            return;
        }
        for (int i = 0; i < temporadas.size(); i++) {
            System.out.println((i + 1) + ". Temporada " + temporadas.get(i).getNumero());
        }
        int idxT = Utils.readIntFromConsole("Escolha a temporada: ") - 1;
        if (idxT < 0 || idxT >= temporadas.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        String titulo = Utils.readLineFromConsole("Título do episódio: ");
        int numero = Utils.readIntFromConsole("Número do episódio: ");
        int duracao = Utils.readIntFromConsole("Duração (min): ");
        temporadas.get(idxT).adicionarEpisodio(new Episodio(titulo, numero, duracao));
        System.out.println("Episódio adicionado com sucesso!");
    }

    private void associarAtorEpisodio() {
        ArrayList<Serie> series = imdb.getSeries();
        if (series.isEmpty()) {
            System.out.println("Não existem séries registadas.");
            return;
        }
        for (int i = 0; i < series.size(); i++) {
            System.out.println((i + 1) + ". " + series.get(i).getTitulo());
        }
        int idxS = Utils.readIntFromConsole("Escolha a série: ") - 1;
        if (idxS < 0 || idxS >= series.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        ArrayList<Temporada> temporadas = series.get(idxS).getTemporadas();
        if (temporadas.isEmpty()) {
            System.out.println("Esta série não tem temporadas.");
            return;
        }
        for (int i = 0; i < temporadas.size(); i++) {
            System.out.println((i + 1) + ". Temporada " + temporadas.get(i).getNumero());
        }
        int idxT = Utils.readIntFromConsole("Escolha a temporada: ") - 1;
        if (idxT < 0 || idxT >= temporadas.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        ArrayList<Episodio> eps = temporadas.get(idxT).getEpisodios();
        if (eps.isEmpty()) {
            System.out.println("Esta temporada não tem episódios.");
            return;
        }
        for (int i = 0; i < eps.size(); i++) {
            System.out.println((i + 1) + ". " + eps.get(i).getTitulo());
        }
        int idxE = Utils.readIntFromConsole("Escolha o episódio: ") - 1;
        if (idxE < 0 || idxE >= eps.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        System.out.println(imdb.listarAtores());
        String nome = Utils.readLineFromConsole("Nome do ator: ");
        Ator ator = imdb.pesquisaAtor(nome);
        if (ator == null) {
            System.out.println("Ator não encontrado.");
            return;
        }
        eps.get(idxE).adicionarAtor(ator);
        System.out.println("Ator associado com sucesso!");
    }

    private void adicionarGeneroSerie() {
        ArrayList<Serie> series = imdb.getSeries();
        if (series.isEmpty()) {
            System.out.println("Não existem séries registadas.");
            return;
        }
        for (int i = 0; i < series.size(); i++) {
            System.out.println((i + 1) + ". " + series.get(i).getTitulo());
        }
        int idx = Utils.readIntFromConsole("Escolha a série: ") - 1;
        if (idx < 0 || idx >= series.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        Genero[] generos = Genero.values();
        for (int i = 0; i < generos.length; i++) {
            System.out.println((i + 1) + ". " + generos[i].getNome());
        }
        int idxG = Utils.readIntFromConsole("Escolha o género: ") - 1;
        if (idxG < 0 || idxG >= generos.length) {
            System.out.println("Opção inválida.");
            return;
        }
        series.get(idx).adicionarGenero(generos[idxG]);
        System.out.println("Género adicionado com sucesso!");
    }
}