package org.example.model;

import java.util.ArrayList;

public class ListaPessoal {

    private ArrayList<Recurso> recursos;

    public ListaPessoal() {
        this.recursos = new ArrayList<>();
    }

    public void adicionar(Recurso r) {
        if (!recursos.contains(r)) {
            recursos.add(r);
        } else {
            System.out.println("Este recurso já está na tua lista pessoal.");
        }
    }

    public void remover(Recurso r) {
        recursos.remove(r);
    }

    public ArrayList<Recurso> getRecursos() {
        // Retorna uma cópia da lista para garantir segurança (Encapsulamento)
        return new ArrayList<>(recursos);
    }

    @Override
    public String toString() {
        String texto = "=== Lista Pessoal ===\n";

        if (recursos.isEmpty()) {
            texto += " (VAZIA)\n";
        } else {
            texto += "Total de itens: " + recursos.size() + "\n";
            for (Recurso r : recursos) {
                texto += " - " + r.getTitulo() + "\n";
            }
        }

        return texto;
    }
}
//lista de filmes e episodios
//lista pessoal vazia
//contains - verifica se já existe para não duplicar
