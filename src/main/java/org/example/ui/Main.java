package org.example.ui;

import org.example.model.*;

/**
 * Classe principal que inicia a aplicação.
 */
public class Main {

    /**
     * Método principal — ponto de entrada da aplicação.
     * @param args argumentos da linha de comandos (não utilizados)
     */
    public static void main(String[] args) {
        DB imdb = null;
        MenuFonteInfo menuFonteInfo = new MenuFonteInfo(imdb);
        menuFonteInfo.run();
    }
}
