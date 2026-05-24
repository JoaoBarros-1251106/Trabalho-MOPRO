package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe abstrata que representa um recurso da plataforma (Filme ou Serie).
 * Implementa Pesquisavel e Serializable.
 */

public abstract class Recurso implements Pesquisavel, Serializable {

    /** Título do recurso. */
    private String titulo;
    /** Ano de lançamento. */
    private int ano;
    /** Lista de géneros associados. */
    private ArrayList<Genero> generos;
    /** Lista de atores associados. */
    private ArrayList<Ator> atores;

    /**
     * Constrói um recurso com título e ano.
     * @param titulo título do recurso
     * @param ano    ano de lançamento
     */

    public Recurso(String titulo, int ano) {
        this.titulo = titulo;
        this.ano = ano;
        this.generos = new ArrayList<>();
        this.atores = new ArrayList<>();
    }

    /**
     * Devolve o título do recurso.
     * @return título
     */

    public String getTitulo() {
        return titulo;
    }

    /**
     * Devolve o ano do recurso.
     * @return ano
     */

    public int getAno() {
        return ano;
    }

    /**
     * Devolve a lista de géneros.
     * @return lista de géneros
     */

    public ArrayList<Genero> getGeneros() {
        return generos;
    }

    /**
     * Devolve a lista de atores.
     * @return lista de atores
     */

    public ArrayList<Ator> getAtores() {
        return atores;
    }

    /**
     * Adiciona um género ao recurso (se ainda não existir).
     * @param genero género a adicionar
     */

    public void adicionarGenero(Genero genero) {
        if (!generos.contains(genero)) {
            generos.add(genero);
        }
    }

    /**
     * Associa um ator ao recurso (se ainda não estiver associado).
     * @param ator ator a associar
     */

    public void adicionarAtor(Ator ator) {
        if (!atores.contains(ator)) {
            atores.add(ator);
        }
    }

    /**
     * Verifica se este recurso é duplicado de outro (mesmo título e ano).
     * @param titulo título a comparar
     * @param ano    ano a comparar
     * @return true se for duplicado
     */

    public boolean isDuplicado(String titulo, int ano) {
        return this.titulo.equalsIgnoreCase(titulo) && this.ano == ano;
    }

    /**
     * Calcula a classificação média do recurso.
     * Implementado de forma diferente em Filme e Serie.
     * @return classificação média
     */

    public abstract double getClassificacaoMedia();


    /**
     * Devolve a categoria da classificação (Fraco, Médio, Bom).
     * Implementado de forma diferente em Filme e Serie.
     * @return categoria textual
     */

    public abstract String getCategoriaClassificacao();

    /**
     * Verifica se o título contém o texto de pesquisa.
     * @param texto texto a pesquisar
     * @return true se o título contiver o texto
     */

    @Override
    public boolean correspondePesquisa(String texto) {
        return titulo.toLowerCase().contains(texto.toLowerCase());
    }

    /**
     * Devolve uma representação textual do recurso.
     * @return título, ano e géneros
     */

    @Override
    public String toString() {
        return titulo + " (" + ano + ") | Géneros: " + generos;
    }
}