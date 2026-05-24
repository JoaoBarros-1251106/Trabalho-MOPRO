package org.example.model;

import org.example.utils.Data;

import java.io.Serializable;

/**
 * Representa um ator que pode participar em filmes e/ou episódios.
 * Implementa Pesquisavel para pesquisa por nome.
 * Implementa Serializable para persistência em ficheiro.
 */
public class Ator implements Pesquisavel, Serializable {
    /**
     * Nome do ator
     */
    private String nome;
    /**
     * Data de nascimento do ator
     */
    private Data dataNascimento;

    /**
     * Constrói um ator
     *
     * @param nome           do ator
     * @param dataNascimento do ator
     */
    public Ator(String nome, Data dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    /**
     * devolve o nome do ator
     *
     * @return nome do ator
     */
    public String getNome() {
        return nome;
    }

    /**
     * devolve a data de nascimento do ator
     *
     * @return data de nascimento do ator
     */
    public Data getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Verifica se o ator tem o nome indicado (ignora maiúsculas/minúsculas)
     *
     * @param nome a comparar
     * @return true se coincidir
     */
    public boolean temNome(String nome) {
        return this.nome.equalsIgnoreCase(nome);
    }

    /**
     * Verifica se o nome contém o texto de pesquisa.
     *
     * @param texto a pesquisar
     * @return true se o nome contiver o texto
     */
    @Override
    public boolean correspondePesquisa(String texto) {
        return nome.toLowerCase().contains(texto.toLowerCase());
    }

    /**
     * Devolve uma representação textual do ator.
     *
     * @return String no formato "Nome [Data de Nascimento]".
     */
    @Override
    public String toString() {
        return nome + " [" + dataNascimento + "]";
    }
}