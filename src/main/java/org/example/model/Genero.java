package org.example.model;

public enum Genero {
    ACTION("Action"),
    ADVENTURE("Adventure"),
    ANIMATION("Animation"),
    BIOGRAPHY("Biography"),
    COMEDY("Comedy"),
    CRIME("Crime"),
    DOCUMENTARY("Documentary"),
    DRAMA("Drama"),
    FAMILY("Family"),
    FANTASY("Fantasy"),
    HISTORY("History"),
    HORROR("Horror"),
    MUSICAL("Musical"),
    MYSTERY("Mystery"),
    ROMANCE("Romance"),
    SCI_FI("Sci-Fi"),
    THRILLER("Thriller"),
    WAR("War"),
    WESTERN("Western");

    private final String nome;

    Genero(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}

//enum - garante que só existem estes géneros - não se pode inventar um novo
//ACTION("Action") - cada valor do enum tem um nome legível associado
//o construtor Generos(String nome) guarda esse nome
//toString() devolve o nome legível (Action) em vez de ACTION me maiúsculas
