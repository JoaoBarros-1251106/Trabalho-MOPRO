package org.example.ui;

import org.example.model.Ator;
import org.example.model.DB;
import org.example.utils.Utils;

/**
 * Classe de suporte dedicada exclusivamente à operação e fluxo de remoção de um ator do sistema.
 * É responsável por procurar o ator introduzido, solicitar a confirmação ao utilizador
 * e invocar a respetiva remoção na base de dados (DB).
 */

public class RemoverAtor {

    private DB imdb;

    /**
     * Construtor da classe RemoverAtor.
     *
     * @param imdb A base de dados que contém o registo do ator a ser removido.
     */


    public RemoverAtor(DB imdb) {
        this.imdb = imdb;
    }

    public void run() {
        System.out.println(imdb.listarAtores());
        String nome = Utils.readLineFromConsole("Nome do ator a remover: ");
        Ator ator = imdb.pesquisaAtor(nome);


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