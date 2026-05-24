package org.example.ui;

import org.example.model.*;
import org.example.utils.Utils;

import java.util.ArrayList;

/**
 * Classe que disponibiliza as opções de gestão focadas em filmes.
 * Engloba funcionalidades como adicionar e remover filmes da base de dados,
 * assim como associar-lhes géneros, atores e efetuar ordenações por título ou classificação.
 */

public class MenuGerirFilmes {

    private DB imdb;
    private String opcao;

    public MenuGerirFilmes(DB imdb) {
        this.imdb = imdb;
    }

    public void run() {
        do {
            System.out.println("\n\n");
            System.out.println("#################################################");
            System.out.println("#              GERIR FILMES                     #");
            System.out.println("#################################################");
            System.out.println("#                                               #");
            System.out.println("#  1. Listar filmes                             #");
            System.out.println("#  2. Adicionar filme                           #");
            System.out.println("#  3. Remover filme                             #");
            System.out.println("#  4. Associar ator a filme                     #");
            System.out.println("#  5. Adicionar género a filme                  #");
            System.out.println("#  6. Filmes ordenados por título               #");
            System.out.println("#  7. Filmes ordenados por classificação        #");
            System.out.println("#                                               #");
            System.out.println("#  0. Voltar                                    #");
            System.out.println("#                                               #");
            System.out.println("#################################################");
            System.out.println();

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            switch (opcao) {
                case "1":
                    listarFilmes();
                    break;
                case "2":
                    adicionarFilme();
                    break;
                case "3":
                    removerFilme();
                    break;
                case "4":
                    associarAtorFilme();
                    break;
                case "5":
                    adicionarGeneroFilme();
                    break;
                case "6":
                    listarFilmesPorTitulo();
                    break;
                case "7":
                    listarFilmesPorClassificacao();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (!opcao.equals("0"));
    }

    /**
     * Lista todos os filmes atualmente registados na plataforma.
     */

    private void listarFilmes() {
        ArrayList<Filme> filmes = imdb.getFilmes();
        if (filmes.isEmpty()) {
            System.out.println("Não existem filmes registados.");
            return;
        }
        System.out.println("\n--- Lista de Filmes ---");
        for (int i = 0; i < filmes.size(); i++) {
            System.out.println((i + 1) + ". " + filmes.get(i));
        }
    }

    /**
     * Solicita os dados básicos para um novo filme (título, ano e duração)
     * e adiciona-o à base de dados.
     */

    private void adicionarFilme() {
        System.out.println("\n--- Adicionar Filme ---");
        String titulo = Utils.readLineFromConsole("Título: ");
        int ano = Utils.readIntFromConsole("Ano: ");
        int duracao = Utils.readIntFromConsole("Duração (min): ");

        Filme novoFilme = new Filme(titulo, ano, duracao);

        try {
            imdb.adicionarRecurso(novoFilme);
            System.out.println("Filme adicionado! Associa pelo menos 1 género e 1 ator.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * Apresenta a lista de filmes, solicita a escolha de um deles
     * e remove-o da plataforma.
     */

    private void removerFilme() {
        ArrayList<Filme> filmes = imdb.getFilmes();
        if (filmes.isEmpty()) {
            System.out.println("Não existem filmes registados.");
            return;
        }
        for (int i = 0; i < filmes.size(); i++) {
            System.out.println((i + 1) + ". " + filmes.get(i).getTitulo());
        }
        int idx = Utils.readIntFromConsole("Escolha o filme a remover: ") - 1;
        if (idx < 0 || idx >= filmes.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        imdb.removerRecurso(filmes.get(idx));
        System.out.println("Filme removido com sucesso!");
    }

    /**
     * Solicita a seleção de um filme e a introdução do nome de um ator.
     * Após pesquisar o ator, associa-o ao filme escolhido.
     */

    private void associarAtorFilme() {
        ArrayList<Filme> filmes = imdb.getFilmes();
        if (filmes.isEmpty()) {
            System.out.println("Não existem filmes registados.");
            return;
        }
        for (int i = 0; i < filmes.size(); i++) {
            System.out.println((i + 1) + ". " + filmes.get(i).getTitulo());
        }
        int idx = Utils.readIntFromConsole("Escolha o filme: ") - 1;
        if (idx < 0 || idx >= filmes.size()) {
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
        filmes.get(idx).adicionarAtor(ator);
        System.out.println("Ator associado com sucesso!");
    }

    /**
     * Solicita a seleção de um filme e de um género, associando-os em seguida.
     */

    private void adicionarGeneroFilme() {
        ArrayList<Filme> filmes = imdb.getFilmes();
        if (filmes.isEmpty()) {
            System.out.println("Não existem filmes registados.");
            return;
        }
        for (int i = 0; i < filmes.size(); i++) {
            System.out.println((i + 1) + ". " + filmes.get(i).getTitulo());
        }
        int idx = Utils.readIntFromConsole("Escolha o filme: ") - 1;
        if (idx < 0 || idx >= filmes.size()) {
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
        filmes.get(idx).adicionarGenero(generos[idxG]);
        System.out.println("Género adicionado com sucesso!");
    }

    /**
     * Lista todos os filmes ordenados alfabeticamente pelo título.
     */


    private void listarFilmesPorTitulo() {
        ArrayList<Filme> filmes = imdb.listarFilmesPorTitulo();
        System.out.println("\n--- Filmes por título ---");
        for (Filme f : filmes) {
            System.out.println(" - " + f.getTitulo() + " (" + f.getAno() + ")");
        }
    }

    /**
     * Lista todos os filmes ordenados pela sua classificação média,
     * juntamente com a categoria correspondente à classificação obtida.
     */

    private void listarFilmesPorClassificacao() {
        ArrayList<Filme> filmes = imdb.listarFilmesPorClassificacao();
        System.out.println("\n--- Filmes por classificação ---");
        for (Filme f : filmes) {
            System.out.println(" - " + f.getTitulo()
                    + " | " + String.format("%.1f", f.getClassificacaoMedia())
                    + " [" + f.getCategoriaClassificacao() + "]");
        }
    }
}