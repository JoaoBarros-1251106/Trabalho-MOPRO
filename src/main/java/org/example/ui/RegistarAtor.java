package org.example.ui;

import org.example.model.Ator;
import org.example.model.DB;
import org.example.utils.Data;
import org.example.utils.Utils;

/**
 * Classe responsável por gerir a interface e o fluxo de registo de um novo ator.
 * Solicita os dados ao utilizador (nome e data de nascimento), apresenta um resumo
 * da informação introduzida e pede confirmação antes de gravar na base de dados.
 */

public class RegistarAtor {
    private DB imdb;

    /**
     * Construtor da classe RegistarAtor.
     *
     * @param imdb A base de dados principal para onde o novo ator será gravado.
     */

    public RegistarAtor(DB imdb) {
        this.imdb = imdb;
    }

    public void run() {

        System.out.println("Novo Ator:");

        Ator novoAtor = introduzDados();
        apresentaDados(novoAtor);
        if (Utils.confirma("Confirma os dados? (S/N)")) {
            imdb.adicionarAtor(novoAtor);
            System.out.println("Ator adicionado com sucesso");
            System.out.println(imdb.listarAtores());
        }
    }

    /**
     * Apresenta na consola a informação textual do objeto Ator instanciado.
     *
     * @param ator Instância da classe Ator cujos dados se pretendem exibir.
     */


    private void apresentaDados(Ator ator) {
        System.out.println(ator);
    }

    /**
     * Pede via consola a leitura do nome e da data de nascimento
     * com o objetivo de construir o objeto do novo ator.
     *
     * @return Um novo objeto Ator instanciado com a informação lida através do utilitário de consola.
     */

    private static Ator introduzDados() {
        String nome = Utils.readLineFromConsole("Introduza o nome do ator: ");
        Data dataNascimento = Utils.readDateFromConsole("Introduza o data de nascimento: ");
        return new Ator(nome, dataNascimento);
    }
}
