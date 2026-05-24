package org.example.persistence;

import org.example.model.DB;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Classe responsável pela serialização e desserialização da DB.
 */
public class Persistencia {

    // Nome do ficheiro onde os dados são guardados
    private static final String FICHEIRO = "imdb_data.dat";

    /**
     * Grava a DB num ficheiro binário (serialização).
     * @param db base de dados a gravar
     * @return true se gravou com sucesso, false caso contrário
     */
    public static boolean gravarDados(DB db) {
        ObjectOutputStream out = null;
        try {
            FileOutputStream fout = new FileOutputStream(FICHEIRO);
            out = new ObjectOutputStream(fout);
            out.writeObject(db);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao gravar dados: " + e.getMessage());
            return false;
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    System.out.println("Erro ao fechar ficheiro: " + e.getMessage());
                }
            }
        }
    }

    /**
     * Lê a DB de um ficheiro binário (desserialização).
     * @return base de dados carregada, ou null se falhou
     */
    public static DB lerDados() {
        ObjectInputStream in = null;
        try {
            FileInputStream fin = new FileInputStream(FICHEIRO);
            in = new ObjectInputStream(fin);
            DB db = (DB) in.readObject();
            return db;
        } catch (java.io.FileNotFoundException e) {
            System.out.println("Ficheiro não encontrado.");
            return null;
        } catch (Exception e) {
            System.out.println("Erro ao ler dados: " + e.getMessage());
            return null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    System.out.println("Erro ao fechar ficheiro: " + e.getMessage());
                }
            }
        }
    }
}