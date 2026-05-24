package org.example.model;

/**
 * Enumeração dos géneros cinematográficos permitidos, baseados nos géneros IMDb.
 * O uso de enum garante que só existem géneros válidos.
 */

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

    /** Nome legível do género. */
    private final String nome;

    /**
     * Constrói um género com o nome legível.
     * @param nome nome do género
     */

    Genero(String nome) {
        this.nome = nome;
    }

    /**
     * Devolve o nome legível do género.
     * @return nome do género
     */

    public String getNome() {
        return nome;
    }

    /**
     * Devolve o nome legível do género.
     * @return nome do género
     */

    @Override
    public String toString() {
        return nome;
    }
}

//enum - garante que só existem estes géneros - não se pode inventar um novo
//ACTION("Action") - cada valor do enum tem um nome legível associado
//o construtor Generos(String nome) guarda esse nome
//toString() devolve o nome legível (Action) em vez de ACTION me maiúsculas
