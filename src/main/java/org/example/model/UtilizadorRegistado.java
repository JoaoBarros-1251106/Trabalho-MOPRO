package org.example.model;

import java.util.ArrayList;

public abstract class UtilizadorRegistado extends Utilizador {

    // REMOVER ESTES ATRIBUTOS DUPLICADOS
    // private String email;
    // private String nome;
    // private String password;

    // Novas listas adicionadas
    private ListaPessoal listaPessoal;
    private ArrayList<Recurso> vistos;

    public UtilizadorRegistado(String username, String email, String password) {

        super(username, email, password);

        this.listaPessoal = new ListaPessoal();
        this.vistos = new ArrayList<>();
    }

    public String getNome() {

        return getUsername();
    }

    @Override
    public String getEmail() {

        return super.getEmail();
    }

    public boolean temNome(String nome) {

        return getUsername().equals(nome);
    }

    public boolean temPassword(String password) {

        return getPassword().equals(password);
    }

    // ==========================================
    // LISTA PESSOAL
    // ==========================================

    public ListaPessoal getListaPessoal() {

        return listaPessoal;
    }

    // ==========================================
    // HISTÓRICO DE VISUALIZAÇÕES
    // ==========================================

    public void adicionarVisualizado(Recurso r) {

        if (!vistos.contains(r)) {

            vistos.add(r);
        }
    }

    public boolean jaViu(Recurso r) {

        return vistos.contains(r);
    }

    // ==========================================
    // INTERAÇÕES (CLASSIFICAR E COMENTAR)
    // ==========================================

    public void classificarFilme(Filme filme, int valor) {

        if (!jaViu(filme)) {

            System.out.println("Tem de ver o filme primeiro.");
            return;
        }

        for (Classificacao c : filme.getClassificacoes()) {

            if (c.getUtilizador().equals(this)) {

                System.out.println("Já classificou este filme.");
                return;
            }
        }

        filme.adicionarClassificacao(new Classificacao(valor, this));
    }

    public void comentarFilme(Filme filme, String texto) {

        if (!jaViu(filme)) {

            System.out.println("Tem de ver o filme primeiro para poder comentar.");
            return;
        }

        filme.adicionarComentario(new Comentario(this, texto));
    }

    @Override
    public String toString() {

        return getUsername() + " <" + getEmail() + ">";
    }
}