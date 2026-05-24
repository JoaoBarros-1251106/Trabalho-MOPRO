package org.example.ui;

import org.example.model.*;

/**
 * Classe principal da aplicação.
 * Contém o ponto de entrada (método main) que inicia a execução do programa,
 * encaminhando o fluxo inicial para o menu de seleção da fonte de informação.
 */

public class Main {
    /**
     * Ponto de entrada principal da aplicação.
     */
    public static void main(String[] args) {
        DB imdb = null;
        MenuFonteInfo menuFonteInfo = new MenuFonteInfo(imdb);
        menuFonteInfo.run();
    }
}
