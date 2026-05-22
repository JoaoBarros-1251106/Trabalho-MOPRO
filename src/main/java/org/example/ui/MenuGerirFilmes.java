package org.example.ui;

import org.example.model.Ator;
import org.example.model.DB;
import org.example.model.Filme;
import org.example.model.Genero;
import org.example.utils.Utils;

import java.util.ArrayList;
import java.util.Comparator;

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
            System.out.println("#                 GERIR FILMES                  #");
            System.out.println("#################################################");
            System.out.println("#                                               #");
            System.out.println("#  1. Adicionar filme                           #");
            System.out.println("#  2. Listar filmes                             #");
            System.out.println("#  3. Remover filme                             #");
            System.out.println("#  4. Pesquisar filme                           #");
            System.out.println("#  5. Ordenar por título                        #");
            System.out.println("#  6. Ordenar por classificação                 #");
            System.out.println("#                                               #");
            System.out.println("#  0. Voltar                                    #");
            System.out.println("#                                               #");
            System.out.println("#################################################");
            System.out.println();

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            switch (opcao) {
                case "1":
                    adicionarFilme();
                    break;
                case "2":
                    System.out.println(imdb.listarFilmes());
                    break;
                case "3":
                    removerFilme();
                    break;
                case "4":
                    pesquisarFilme();
                    break;
                case "5":
                    ordenarPorTitulo();
                    break;
                case "6":
                    ordenarPorClassificacao();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (!opcao.equals("0"));
    }

    private void adicionarFilme() {
        System.out.println("\n--- Adicionar Filme ---");
        String titulo = Utils.readLineFromConsole("Introduza o título do filme: ");
        String anoStr = Utils.readLineFromConsole("Introduza o ano: ");
        int ano = Integer.parseInt(anoStr);
        String duracaoStr = Utils.readLineFromConsole("Introduza a duração (min): ");
        int duracao = Integer.parseInt(duracaoStr);

        System.out.println("\nGéneros disponíveis:");
        Genero[] generos = Genero.values();
        for (int i = 0; i < generos.length; i++) {
            System.out.println(i + " - " + generos[i]);
        }
        String genStr = Utils.readLineFromConsole("Escolha o número do género: ");
        Genero generoEscolhido = generos[Integer.parseInt(genStr)];

        System.out.println("\nAtores disponíveis:");
        ArrayList<Ator> atores = imdb.getAtores();
        if (atores.isEmpty()) {
            System.out.println("ERRO: Não existem atores registados na base de dados.");
            return;
        }

        for (int i = 0; i < atores.size(); i++) {
            System.out.println(i + " - " + atores.get(i).getNome());
        }
        String atorStr = Utils.readLineFromConsole("Escolha o número do ator: ");
        Ator atorEscolhido = atores.get(Integer.parseInt(atorStr));

        Filme novoFilme = new Filme(titulo, ano, duracao, generoEscolhido, atorEscolhido);
        imdb.adicionarFilme(novoFilme);
        System.out.println("Filme '" + titulo + "' adicionado com sucesso!");
    }

    private void removerFilme() {
        System.out.println("\n--- Remover Filme ---");
        ArrayList<Filme> filmes = imdb.getFilmes();

        if (filmes.isEmpty()) {
            System.out.println("Não existem filmes registados.");
            return;
        }

        for (int i = 0; i < filmes.size(); i++) {
            System.out.println(i + " - " + filmes.get(i).getTitulo());
        }

        String indexStr = Utils.readLineFromConsole("Escolha o número do filme a remover: ");
        int index = Integer.parseInt(indexStr);

        if (index >= 0 && index < filmes.size()) {
            Filme f = filmes.get(index);
            imdb.removerFilme(f);
            System.out.println("Filme removido com sucesso!");
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private void pesquisarFilme() {
        System.out.println("\n--- Pesquisar Filme ---");
        String texto = Utils.readLineFromConsole("Introduza o texto a pesquisar: ");
        boolean encontrou = false;

        for (int i = 0; i < imdb.getFilmes().size(); i++) {
            Filme f = imdb.getFilmes().get(i);
            if (f.correspondePesquisa(texto)) {
                System.out.println(" - " + f.getTitulo() + " (" + f.getAno() + ")");
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum filme encontrado.");
        }
    }

    private void ordenarPorTitulo() {
        System.out.println("\n--- Filmes Ordenados por Título ---");
        ArrayList<Filme> filmes = imdb.getFilmes();

        if (filmes.isEmpty()) {
            System.out.println("Não existem filmes registados.");
            return;
        }

        // Ordenação por título pedida no Passo 11
        filmes.sort(Comparator.comparing(Filme::getTitulo));

        for (int i = 0; i < filmes.size(); i++) {
            Filme f = filmes.get(i);
            System.out.println(" - " + f.getTitulo() + " (" + f.getAno() + ")");
        }
    }

    private void ordenarPorClassificacao() {
        System.out.println("\n--- Filmes Ordenados por Classificação Média ---");
        ArrayList<Filme> filmes = imdb.getFilmes();

        if (filmes.isEmpty()) {
            System.out.println("Não existem filmes registados.");
            return;
        }

        // Ordenação por média pedida no Passo 11
        filmes.sort((f1, f2) -> Double.compare(f2.calcularMediaClassificacoes(), f1.calcularMediaClassificacoes()));

        for (int i = 0; i < filmes.size(); i++) {
            Filme f = filmes.get(i);
            System.out.println(" - " + f.getTitulo() + " | Média: " + f.getClassificacaoMedia());
        }
    }
}