package org.example.ui;

import org.example.model.DB;
import org.example.model.Espectador;
import org.example.model.Filme;
import org.example.model.Recurso;
import org.example.utils.Utils;

import java.util.ArrayList;

public class MenuUtilizadorRegistado {
    private DB imdb;
    private Espectador utilizador;
    private String opcao;

    public MenuUtilizadorRegistado(DB imdb, Espectador utilizador) {
        this.imdb = imdb;
        this.utilizador = utilizador;
    }

    public void run() {
        do {
            System.out.println("\n\n");
            System.out.println("#################################################");
            System.out.println("#               MENU UTILIZADOR                 #");
            System.out.println("#################################################");
            System.out.println("#                                               #");
            System.out.println("#  1. Adicionar à lista pessoal                 #");
            System.out.println("#  2. Remover da lista                          #");
            System.out.println("#  3. Consultar lista                           #");
            System.out.println("#  4. Marcar como visto                         #");
            System.out.println("#  5. Classificar filme                         #");
            System.out.println("#  6. Comentar filme                            #");
            System.out.println("#  7. Ver filmes/séries disponíveis             #");
            System.out.println("#                                               #");
            System.out.println("#  0. Voltar                                    #");
            System.out.println("#                                               #");
            System.out.println("#################################################");
            System.out.println();

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            switch (opcao) {
                case "1":
                    adicionarALista();
                    break;
                case "2":
                    removerDaLista();
                    break;
                case "3":
                    consultarLista();
                    break;
                case "4":
                    marcarComoVisto();
                    break;
                case "5":
                    classificarFilme();
                    break;
                case "6":
                    comentarFilme();
                    break;
                case "7":
                    System.out.println(imdb.listarFilmes());
                    System.out.println(imdb.listarSeries());
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (!opcao.equals("0"));
    }

    // ==========================================
    // 1. ADICIONAR À LISTA PESSOAL
    // ==========================================
    private void adicionarALista() {
        System.out.println("\n--- Adicionar à Lista Pessoal ---");
        ArrayList<Filme> filmes = imdb.getFilmes();

        if (filmes.isEmpty()) {
            System.out.println("Não existem filmes disponíveis na plataforma.");
            return;
        }

        for (int i = 0; i < filmes.size(); i++) {
            System.out.println(i + " - " + filmes.get(i).getTitulo());
        }

        String escolhaStr = Utils.readLineFromConsole("Escolha o número do filme para adicionar: ");
        int index = Integer.parseInt(escolhaStr);
        Filme f = filmes.get(index);

        utilizador.getListaPessoal().adicionar(f);
        System.out.println("Adicionado com sucesso à tua lista pessoal!");
    }

    // ==========================================
    // 2. REMOVER DA LISTA PESSOAL
    // ==========================================
    private void removerDaLista() {
        System.out.println("\n--- Remover da Lista Pessoal ---");
        ArrayList<Recurso> recursos = utilizador.getListaPessoal().getRecursos();

        if (recursos.isEmpty()) {
            System.out.println("A tua lista pessoal está vazia.");
            return;
        }

        for (int i = 0; i < recursos.size(); i++) {
            System.out.println(i + " - " + recursos.get(i).getTitulo());
        }

        String escolhaStr = Utils.readLineFromConsole("Escolha o número do recurso a remover: ");
        int index = Integer.parseInt(escolhaStr);
        Recurso r = recursos.get(index);

        utilizador.getListaPessoal().remover(r);
        System.out.println("Removido com sucesso da tua lista!");
    }

    // ==========================================
    // 3. CONSULTAR LISTA PESSOAL
    // ==========================================
    private void consultarLista() {
        System.out.println("\n" + utilizador.getListaPessoal());
    }

    // ==========================================
    // 4. MARCAR COMO VISTO
    // ==========================================
    private void marcarComoVisto() {
        System.out.println("\n--- Marcar Filme como Visto ---");
        ArrayList<Filme> filmes = imdb.getFilmes();

        if (filmes.isEmpty()) {
            System.out.println("Não existem filmes disponíveis na plataforma.");
            return;
        }

        for (int i = 0; i < filmes.size(); i++) {
            System.out.println(i + " - " + filmes.get(i).getTitulo());
        }

        String escolhaStr = Utils.readLineFromConsole("Escolha o número do filme: ");
        int index = Integer.parseInt(escolhaStr);
        Filme filme = filmes.get(index);

        // A linha exata que pediste
        filme.marcarComoVisto(utilizador);

        System.out.println("Filme '" + filme.getTitulo() + "' marcado como visto!");
    }

    // ==========================================
    // 5. CLASSIFICAR FILME
    // ==========================================
    private void classificarFilme() {
        System.out.println("\n--- Classificar Filme ---");
        ArrayList<Filme> filmes = imdb.getFilmes();

        if (filmes.isEmpty()) {
            System.out.println("Não existem filmes disponíveis na plataforma.");
            return;
        }

        for (int i = 0; i < filmes.size(); i++) {
            System.out.println(i + " - " + filmes.get(i).getTitulo());
        }

        String escolhaStr = Utils.readLineFromConsole("Escolha o número do filme a classificar: ");
        int index = Integer.parseInt(escolhaStr);
        Filme filme = filmes.get(index);

        String notaStr = Utils.readLineFromConsole("Introduza a classificação (ex: 1 a 10): ");
        int nota = Integer.parseInt(notaStr);

        // A lógica de verificar se já viu está embutida na classe UtilizadorRegistado
        utilizador.classificarFilme(filme, nota);
    }

    // ==========================================
    // 6. COMENTAR FILME
    // ==========================================
    private void comentarFilme() {
        System.out.println("\n--- Comentar Filme ---");
        ArrayList<Filme> filmes = imdb.getFilmes();

        if (filmes.isEmpty()) {
            System.out.println("Não existem filmes disponíveis na plataforma.");
            return;
        }

        for (int i = 0; i < filmes.size(); i++) {
            System.out.println(i + " - " + filmes.get(i).getTitulo());
        }

        String escolhaStr = Utils.readLineFromConsole("Escolha o número do filme para comentar: ");
        int index = Integer.parseInt(escolhaStr);
        Filme filme = filmes.get(index);

        String texto = Utils.readLineFromConsole("Escreva o seu comentário: ");

        // A lógica de verificar se já viu está embutida na classe UtilizadorRegistado
        utilizador.comentarFilme(filme, texto);
    }
}