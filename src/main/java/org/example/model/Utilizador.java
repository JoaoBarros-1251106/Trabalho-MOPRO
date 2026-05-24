package org.example.model;

public abstract class Utilizador {

    private String username;
    private String email;
    private String password;

    public Utilizador(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}