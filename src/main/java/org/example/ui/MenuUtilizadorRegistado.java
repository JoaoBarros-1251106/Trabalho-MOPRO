package org.example.ui;

import org.example.model.*;
import org.example.utils.Utils;

public class MenuUtilizadorRegistado {
    private DB imdb;
    private Espectador utilizador;
    private String opcao;

    public MenuUtilizadorRegistado(DB imdb, Espectador utilizador) {
        this.utilizador = utilizador;
        this.imdb = imdb;
    }

    public void run() {
        do {
            System.out.println("\n\n");
            System.out.println("#################################################");
            System.out.println("#                     MENU                      #");
            System.out.println("#################################################");
            System.out.println("#                                               #");
            System.out.println("#  1. Ver atores                                #");
            System.out.println("#  2. Ver utilizadores                          #");
            System.out.println("#  3. Ver perfil                                #");
            System.out.println("#                                               #");
            System.out.println("#  0. Voltar                                    #");
            System.out.println("#                                               #");
            System.out.println("#################################################");
            System.out.println();
            //adicionado

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            switch (opcao) {
                // adicionado
                case "1":
                    System.out.println(imdb.listarAtores());
                    break;

                case "2":
                    System.out.println(imdb.listarUtilizadores());
                    break;

                case "3":
                    System.out.println(utilizador);
                    break;

                case "0":
                    break;

            }
        }
        while (!opcao.equals("0"));
    }
}

