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
     * Constrói o menu de registo de ator.
     * @param imdb base de dados da plataforma
     */
    public RegistarAtor(DB imdb) {
        this.imdb = imdb;
    }

    /**
     * Executa o processo de registo de um novo ator.
     * Solicita os dados, apresenta-os e pede confirmação antes de guardar.
     */
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
     * Apresenta na consola a informação textual do ator.
     * @param ator ator cujos dados se pretendem exibir
     */
    private void apresentaDados(Ator ator) {
        System.out.println(ator);
    }

    /**
     * Lê o nome e a data de nascimento do ator a partir da consola.
     * @return novo objeto Ator com os dados introduzidos
     */
    private static Ator introduzDados() {
        String nome = Utils.readLineFromConsole("Introduza o nome do ator: ");
        Data dataNascimento = Utils.readDateFromConsole("Introduza o data de nascimento: ");
        return new Ator(nome, dataNascimento);
    }
}