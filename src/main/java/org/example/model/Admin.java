package org.example.model;

public class Admin extends UtilizadorRegistado {
    public Admin(String email, String nome, String password) {
        super(email, nome, password);
    }
}

//extends - herda de UtilizadorRegistado
//super - chama o construtor da classe mãe