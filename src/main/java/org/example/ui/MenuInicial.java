package org.example.ui;

import org.example.model.Admin;
import org.example.model.DB;
import org.example.model.Espectador;
import org.example.model.UtilizadorRegistado;
import org.example.utils.Utils;

/**
 * Classe que apresenta o primeiro menu interativo principal da aplicação.
 * É a partir daqui que o utilizador decide se deseja aceder à plataforma
 * num modo de visualização livre (sem autenticação) ou realizar o login na sua conta.
 */
public class MenuInicial {

    private DB imdb;
    private String opcao;

    /**
     * Constrói o menu inicial.
     * @param imdb base de dados da plataforma
     */
    public MenuInicial(DB imdb) {
        this.imdb = imdb;
    }

    /**
     * Inicia o ciclo de execução do menu inicial.
     * Permite aceder sem login ou autenticar como administrador ou espectador.
     */
    public void run() {
        do {
            System.out.println("\n\n");
            System.out.println("#################################################");
            System.out.println("#                     MENU                      #");
            System.out.println("#################################################");
            System.out.println("#                                               #");
            System.out.println("#  1. Ver sem login                             #");
            System.out.println("#  2. Login                                     #");
            System.out.println("#                                               #");
            System.out.println("#  0. Voltar                                    #");
            System.out.println("#                                               #");
            System.out.println("#################################################");
            System.out.println();

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            if (opcao.equals("1")) {
                new MenuSemLogin(imdb).run();

            } else if (opcao.equals("2")) {
                UtilizadorRegistado ur = null;
                while (ur == null) {
                    String nome = Utils.readLineFromConsole("Username: ");
                    String password = Utils.readLineFromConsole("Password: ");
                    try {
                        ur = imdb.login(nome, password);
                        if (ur == null) throw new Exception("Credenciais inválidas.");
                        System.out.println("Bem-vindo, " + ur.getNome() + "!");
                        if (ur instanceof Admin) {
                            new MenuAdministrador(imdb).run();
                        } else if (ur instanceof Espectador) {
                            new MenuUtilizadorRegistado(imdb, (Espectador) ur).run();
                        }
                    } catch (Exception e) {
                        System.out.println("ERRO: " + e.getMessage());
                        String tentar = Utils.readLineFromConsole("Tentar novamente? (S/N): ");
                        if (tentar.equalsIgnoreCase("N")) break;
                    }
                }

            } else if (!opcao.equals("0")) {
                System.out.println("Opção inválida!");
            }

        } while (!opcao.equals("0"));
    }
}