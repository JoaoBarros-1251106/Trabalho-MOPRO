package org.example.model;

public interface Pesquisavel {
    boolean correspondePesquisa(String texto);
}

//interface - qualquer classe que a implemente é obrigada a ter o metodo correspondePesquisa
//implementado por Ator, Filme e Serie