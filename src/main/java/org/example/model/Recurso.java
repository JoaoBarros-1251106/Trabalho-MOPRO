package org.example.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe abstrata que representa um Recurso audiovisual (Filme ou Série).
 * Contém a informação partilhada por ambos.
 */
public abstract class Recurso implements Pesquisavel {

    private String titulo;
    private int ano;
    private ArrayList<Genero> generos;
    private ArrayList<Ator> atores;

    /**
     * Construtor da classe Recurso.
     * @param titulo Título do recurso.
     * @param ano Ano de lançamento.
     * @param genero Primeiro género obrigatório (Garante a regra do enunciado).
     */
    public Recurso(String titulo, int ano, Genero genero) {
        this.titulo = titulo;
        this.ano = ano;
        this.generos = new ArrayList<>();
        this.atores = new ArrayList<>();
        this.generos.add(genero); // Garante que tem pelo menos 1 género
    }

    public String getTitulo() { return titulo; }
    public int getAno() { return ano; }

    // Retornar uma cópia da lista garante segurança (Encapsulamento):
    public ArrayList<Genero> getGeneros() {
        return new ArrayList<>(generos);
    }

    public ArrayList<Ator> getAtores() {
        return new ArrayList<>(atores);
    }

    public void adicionarGenero(Genero genero) {
        if (!generos.contains(genero)) {
            generos.add(genero);
        }
    }

    public void adicionarAtor(Ator ator) {
        if (!atores.contains(ator)) {
            atores.add(ator);
        }
    }

    public abstract double getClassificacaoMedia();
    public abstract String getCategoriaClassificacao();

    @Override
    public boolean correspondePesquisa(String texto) {
        return titulo.toLowerCase().contains(texto.toLowerCase());
    }

    /**
     * Substitui o "isDuplicado". Usa a lógica padrão do Java para verificar se dois recursos
     * são o mesmo com base no Título e Ano.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Recurso recurso = (Recurso) obj;
        return ano == recurso.ano && titulo.equalsIgnoreCase(recurso.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo.toLowerCase(), ano);
    }

    @Override
    public String toString() {
        return titulo + " (" + ano + ") | Géneros: " + generos;
    }
}