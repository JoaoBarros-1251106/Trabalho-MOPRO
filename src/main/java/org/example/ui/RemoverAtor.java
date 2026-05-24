package org.example.ui;

import org.example.model.Ator;
import org.example.model.DB;
import org.example.utils.Utils;

public class RemoverAtor {

    private DB imdb;

    public RemoverAtor(DB imdb) {
        this.imdb = imdb;
    }

    public void run() {
        System.out.println(imdb.listarAtores());
        String nome = Utils.readLineFromConsole("Nome do ator a remover: ");
        Ator ator = imdb.pesquisaAtor(nome);

        // Verifica se o ator existe antes de continuar
        if (ator == null) {
            System.out.println("Ator não encontrado.");
            return;
        }

        System.out.println(ator);
        if (Utils.confirma("Confirma que pretende remover? (S/N)")) {
            imdb.removerAtor(ator);
            System.out.println("Ator removido com sucesso.");
            System.out.println(imdb.listarAtores());
        }
    }
}