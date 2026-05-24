package org.example.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Classe utilitária com métodos auxiliares para leitura de dados da consola.
 * Fornecida pela docente como parte do template do projeto.
 */
public class Utils {

    /**
     * Lê uma linha de texto da consola.
     * @param strPrompt mensagem a apresentar ao utilizador
     * @return texto introduzido pelo utilizador
     */
    static public String readLineFromConsole(String strPrompt) {
        try {
            System.out.print(strPrompt);
            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);
            return in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Lê um número inteiro da consola. Repete até o valor introduzido ser válido.
     * @param strPrompt mensagem a apresentar ao utilizador
     * @return número inteiro introduzido pelo utilizador
     */
    public static int readIntFromConsole(String strPrompt) {
        do {
            try {
                String strInt = readLineFromConsole(strPrompt);
                int iInt = Integer.parseInt(strInt);
                return iInt;
            } catch (NumberFormatException ex) {
                //Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    /**
     * Lê um número decimal da consola. Repete até o valor introduzido ser válido.
     * @param strPrompt mensagem a apresentar ao utilizador
     * @return número decimal introduzido pelo utilizador
     */
    public static double readDoubleFromConsole(String strPrompt) {
        do {
            try {
                String strDouble = readLineFromConsole(strPrompt);
                double iDouble = Double.parseDouble(strDouble);
                return iDouble;
            } catch (NumberFormatException ex) {
                //Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    /**
     * Lê uma data da consola no formato dd-MM-yyyy. Repete até o valor introduzido ser válido.
     * @param strPrompt mensagem a apresentar ao utilizador
     * @return data introduzida pelo utilizador
     */
    static public Data readDateFromConsole(String strPrompt) {
        do {
            try {
                String strData = readLineFromConsole(strPrompt + "(formato: dd-MM-yyyy)");
                String[] arr = strData.split("-");
                int dia = Integer.parseInt(arr[0]);
                int mes = Integer.parseInt(arr[1]);
                int ano = Integer.parseInt(arr[2]);
                return new Data(ano, mes, dia);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                System.out.println("--> " + ex.toString());
            }
        } while (true);
    }

    /**
     * Solicita uma confirmação ao utilizador (S/N). Repete até receber uma resposta válida.
     * @param sMessage mensagem a apresentar ao utilizador
     * @return true se o utilizador confirmou com "S", false se respondeu "N"
     */
    static public boolean confirma(String sMessage) {
        String strConfirma;
        do {
            strConfirma = Utils.readLineFromConsole(sMessage);
        } while (!strConfirma.equalsIgnoreCase("s") && !strConfirma.equalsIgnoreCase("n"));

        return strConfirma.equalsIgnoreCase("s");
    }
}