package org.example.ui;

import org.example.model.Ator;
import org.example.model.DB;
import org.example.model.Episodio;
import org.example.model.Genero;
import org.example.model.Serie;
import org.example.model.Temporada;
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
            System.out.println("#                 GERIR SÉRIES                  #");
            System.out.println("#################################################");
            System.out.println("#                                               #");
            System.out.println("#  1. Adicionar série                           #");
            System.out.println("#  2. Adicionar temporada                       #");
            System.out.println("#  3. Adicionar episódio                        #");
            System.out.println("#  4. Listar séries                             #");
            System.out.println("#  5. Pesquisar série                           #");
            System.out.println("#                                               #");
            System.out.println("#  0. Voltar                                    #");
            System.out.println("#                                               #");
            System.out.println("#################################################");
            System.out.println();

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            switch (opcao) {
                case "1":
                    adicionarSerie();
                    break;
                case "2":
                    adicionarTemporada();
                    break;
                case "3":
                    adicionarEpisodio();
                    break;
                case "4":
                    // Assume que a classe DB tem um método listarSeries()
                    System.out.println(imdb.listarSeries());
                    break;
                case "5":
                    pesquisarSerie();
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
    // 1. ADICIONAR SÉRIE
    // ==========================================
    private void adicionarSerie() {
        System.out.println("\n--- Adicionar Série ---");
        String titulo = Utils.readLineFromConsole("Introduza o título da série: ");
        String anoStr = Utils.readLineFromConsole("Introduza o ano: ");
        int ano = Integer.parseInt(anoStr);

        System.out.println("\nGéneros disponíveis:");
        Genero[] generos = Genero.values();
        for (int i = 0; i < generos.length; i++) {
            System.out.println(i + " - " + generos[i]);
        }
        String genStr = Utils.readLineFromConsole("Escolha o número do género: ");
        Genero generoEscolhido = generos[Integer.parseInt(genStr)];

        Serie novaSerie = new Serie(titulo, ano, generoEscolhido);
        imdb.adicionarSerie(novaSerie);
        System.out.println("Série '" + titulo + "' adicionada com sucesso!");
    }

    // ==========================================
    // 2. ADICIONAR TEMPORADA
    // ==========================================
    private void adicionarTemporada() {
        System.out.println("\n--- Adicionar Temporada ---");
        ArrayList<Serie> series = imdb.getSeries();

        if (series.isEmpty()) {
            System.out.println("ERRO: Não existem séries registadas. Adicione uma série primeiro.");
            return;
        }

        // Escolher série
        for (int i = 0; i < series.size(); i++) {
            System.out.println(i + " - " + series.get(i).getTitulo());
        }
        String serieStr = Utils.readLineFromConsole("Escolha o número da série: ");
        Serie serieEscolhida = series.get(Integer.parseInt(serieStr));

        // Dados da temporada
        String numeroStr = Utils.readLineFromConsole("Introduza o número da temporada: ");
        int numero = Integer.parseInt(numeroStr);
        String anoStr = Utils.readLineFromConsole("Introduza o ano da temporada: ");
        int ano = Integer.parseInt(anoStr);

        // Criar e associar
        Temporada novaTemporada = new Temporada(numero, ano);
        serieEscolhida.adicionarTemporada(novaTemporada);
        System.out.println("Temporada " + numero + " adicionada à série '" + serieEscolhida.getTitulo() + "'!");
    }

    // ==========================================
    // 3. ADICIONAR EPISÓDIO
    // ==========================================
    private void adicionarEpisodio() {
        System.out.println("\n--- Adicionar Episódio ---");
        ArrayList<Serie> series = imdb.getSeries();

        if (series.isEmpty()) {
            System.out.println("ERRO: Não existem séries registadas.");
            return;
        }

        // Escolher série
        for (int i = 0; i < series.size(); i++) {
            System.out.println(i + " - " + series.get(i).getTitulo());
        }
        String serieStr = Utils.readLineFromConsole("Escolha o número da série: ");
        Serie serieEscolhida = series.get(Integer.parseInt(serieStr));

        // Escolher temporada
        ArrayList<Temporada> temporadas = serieEscolhida.getTemporadas();
        if (temporadas.isEmpty()) {
            System.out.println("ERRO: Esta série ainda não tem temporadas. Adicione uma temporada primeiro.");
            return;
        }

        for (int i = 0; i < temporadas.size(); i++) {
            System.out.println(i + " - Temporada " + temporadas.get(i).getNumero());
        }
        String tempStr = Utils.readLineFromConsole("Escolha o número da temporada: ");
        Temporada temporadaEscolhida = temporadas.get(Integer.parseInt(tempStr));

        // Dados do episódio
        String titulo = Utils.readLineFromConsole("Introduza o título do episódio: ");
        String numeroStr = Utils.readLineFromConsole("Introduza o número do episódio: ");
        int numero = Integer.parseInt(numeroStr);
        String duracaoStr = Utils.readLineFromConsole("Introduza a duração (min): ");
        int duracao = Integer.parseInt(duracaoStr);

        // Atores da plataforma
        ArrayList<Ator> atores = imdb.getAtores();
        if (atores.isEmpty()) {
            System.out.println("ERRO: Não existem atores na base de dados. Registe pelo menos um ator.");
            return;
        }

        System.out.println("\nAtores disponíveis:");
        for (int i = 0; i < atores.size(); i++) {
            System.out.println(i + " - " + atores.get(i).getNome());
        }
        String atorStr = Utils.readLineFromConsole("Escolha o ator principal (obrigatório): ");
        Ator atorPrincipal = atores.get(Integer.parseInt(atorStr));

        // Criar episódio
        Episodio novoEpisodio = new Episodio(titulo, numero, duracao, atorPrincipal);

        // Ciclo para adicionar mais atores
        boolean adicionarMais = true;
        while (adicionarMais) {
            String resposta = Utils.readLineFromConsole("Deseja adicionar mais atores a este episódio? (S/N): ");
            if (resposta.equalsIgnoreCase("S")) {
                for (int i = 0; i < atores.size(); i++) {
                    System.out.println(i + " - " + atores.get(i).getNome());
                }
                String atorExtraStr = Utils.readLineFromConsole("Escolha o número do ator extra: ");
                Ator atorExtra = atores.get(Integer.parseInt(atorExtraStr));
                novoEpisodio.adicionarAtor(atorExtra);
            } else {
                adicionarMais = false;
            }
        }

        // Adicionar à temporada
        temporadaEscolhida.adicionarEpisodio(novoEpisodio);
        System.out.println("Episódio adicionado com sucesso à Temporada " + temporadaEscolhida.getNumero() + "!");
    }

    // ==========================================
    // 5. PESQUISAR SÉRIE
    // ==========================================
    private void pesquisarSerie() {
        System.out.println("\n--- Pesquisar Série ---");
        String texto = Utils.readLineFromConsole("Introduza o texto a pesquisar (título): ");
        boolean encontrou = false;

        for (int i = 0; i < imdb.getSeries().size(); i++) {
            Serie s = imdb.getSeries().get(i);
            if (s.correspondePesquisa(texto)) {
                System.out.println(" - " + s.getTitulo() + " (" + s.getAno() + ")");
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma série encontrada com esse título.");
        }
    }
}