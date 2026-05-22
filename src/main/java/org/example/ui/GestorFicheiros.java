package org.example.ui;

import org.example.model.DB;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class GestorFicheiros {

    private static final String NOME_FICHEIRO = "base_dados.txt";

    // ==========================================
    // GUARDAR
    // ==========================================
    public static void guardar(DB db) {
        try {
            // O PrintWriter escreve texto normal num ficheiro
            PrintWriter writer = new PrintWriter(new File(NOME_FICHEIRO));

            // Aqui terás de escrever a lógica manual para guardar.
            // Exemplo fictício para os atores:
            // writer.println("--- ATORES ---");
            // for(Ator a : db.getAtores()) {
            //     writer.println(a.getNome() + ";" + a.getDataNascimento());
            // }

            writer.println("DB_GUARDADA_COM_SUCESSO"); // Apenas um placeholder

            writer.close();
            System.out.println("Dados guardados com sucesso no ficheiro de texto!");

        } catch (Exception e) {
            // Apanhamos a exceção aqui dentro para não ter de usar "throws" no método
            System.out.println("Erro ao guardar o ficheiro: " + e.getMessage());
        }
    }

    // ==========================================
    // CARREGAR
    // ==========================================
    public static DB carregar() {
        try {
            File ficheiro = new File(NOME_FICHEIRO);

            if (!ficheiro.exists()) {
                System.out.println("Ficheiro não encontrado. Vai ser criada uma nova base de dados vazia.");
                return new DB("www.imdb.com");
            }

            Scanner scanner = new Scanner(ficheiro);
            DB db = new DB("www.imdb.com");

            // Aqui terás de ler linha a linha e reconstruir os objetos.
            // Exemplo fictício:
            // while (scanner.hasNextLine()) {
            //     String linha = scanner.nextLine();
            //     String[] partes = linha.split(";");
            //     // Reconstruir o ator, filme, etc...
            // }

            scanner.close();
            System.out.println("Dados carregados com sucesso do ficheiro de texto!");
            return db;

        } catch (Exception e) {
            // Apanhamos a exceção aqui dentro
            System.out.println("Erro ao ler o ficheiro. A iniciar DB vazia... Erro: " + e.getMessage());
            return new DB("www.imdb.com");
        }
    }
}