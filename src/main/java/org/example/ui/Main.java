package org.example.ui;

import org.example.model.*;

/**
 * Classe principal da aplicação.
 * Contém o ponto de entrada (método main) que inicia a execução do programa,
 * encaminhando o fluxo inicial para o menu de seleção da fonte de informação.
 */

public class Main {
    /**
     * Ponto de entrada do sistema.
     * Inicializa o contexto da plataforma enviando a referência da base de dados (nula inicialmente)
     * para o menu responsável pelo carregamento da fonte de informação.
     *
     * @param args Argumentos de linha de comandos passados no arranque da aplicação.
     */
    public static void main(String[] args) {
        DB imdb = null;
        MenuFonteInfo menuFonteInfo = new MenuFonteInfo(imdb);
        menuFonteInfo.run();
    }
}
